package com.sophos.transmilenio.controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.transmilenio.beans.Estacion;
import com.sophos.transmilenio.beans.Ruta;
import com.sophos.transmilenio.daos.RutaDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(
		value="API CRUD Ruta",
		protocols = "HTTP/REST",
		consumes = "INTERNO"
		)
@CrossOrigin
@RestController
@RequestMapping("/rutas")
public class RutaController {
	
	@Autowired
	private RutaDao dao; 

	@Autowired
	EntityManager em;
	
	@ApiOperation(value = "Consulta de estaciones por ruta", response = Iterable.class, httpMethod = "GET")
	@GetMapping("/{codigo}/estaciones")
	public ResponseEntity<List<Estacion>> getEstacion2(@PathVariable String codigo){
		   TypedQuery<Estacion> query
		      = em.createQuery("SELECT d FROM Ruta e INNER JOIN e.estaciones d WHERE e.codRuta = '"+codigo+"'", Estacion.class);
		    List<Estacion> resultList = query.getResultList();
		return new ResponseEntity<List<Estacion>>(resultList,HttpStatus.OK);
	}
	
	@ApiOperation(value = "Consulta de rutas", response = Iterable.class, httpMethod = "GET")
	@GetMapping("")
	public ResponseEntity<Iterable<Ruta>> verRuta(){
		if(dao.count()>0) {
			return new ResponseEntity<Iterable<Ruta>>(dao.findAll(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Iterable<Ruta>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Consulta de rutas por id", response = Ruta.class, httpMethod = "GET")
	@GetMapping("/{id}")
	public ResponseEntity<Ruta> verEstacionId(@PathVariable String id){
		Optional<Ruta> ruta = dao.findById(id);
		if(ruta.isPresent()) {
			return new ResponseEntity<Ruta>(ruta.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Ruta>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Registro de rutas", response = String.class, httpMethod = "POST")
	@PostMapping("/listas")
	public  ResponseEntity<String> addRuta(@RequestBody Ruta ruta) {
		List<Ruta> rutas = (List<Ruta>) dao.findAll();
		boolean existe = false;
		for (int i = 0; i < rutas.size()&&!existe; i++) {
			if(rutas.get(i).getCodRuta().equals(ruta.getCodRuta())) {
				existe = true;
			}
		}
		if(!ruta.getCodRuta().isEmpty()&&!ruta.getNombre().isEmpty()&&!existe) {
			try {
				dao.save(ruta);
			} catch (Exception e) {
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
			   return new ResponseEntity<String>(HttpStatus.CREATED);	
		}else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@Transactional
	@ApiOperation(value = "Borrado de ruta por id", response = String.class, httpMethod = "DELETE")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRuta(@PathVariable String id) {
		 
		em.createNativeQuery("DELETE FROM EstacionRuta WHERE codRuta= ? ")
	      .setParameter(1, id)
	      .executeUpdate();
			
		try {
			dao.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("No se pudo eliminar la ruta",HttpStatus.BAD_REQUEST);
		}
		    return new ResponseEntity<String>("Ruta eliminada",HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar ruta", response = String.class, httpMethod = "PUT")
	@PutMapping("/")
	@Transactional       
	public  ResponseEntity<String> actualizarEstacion(@RequestBody Ruta ruta) {
	    System.out.println(ruta.getCodRuta());
	    System.out.println(ruta.getNombre());
		if(!ruta.getCodRuta().isEmpty()&&!ruta.getNombre().isEmpty()) {
		    	em.createNativeQuery("UPDATE Ruta SET nombre = ?, inicioOperacion = ?, finOperacion = ? WHERE codRuta= ? ")
			      .setParameter(1, ruta.getNombre())
			      .setParameter(2, ruta.getInicioOperacion())
			      .setParameter(3, ruta.getInicioOperacion())
			      .setParameter(4, ruta.getCodRuta())
			      .executeUpdate();		    	
		    return new ResponseEntity<String>(HttpStatus.CREATED);
	    }else {
	    	return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	    }
		
	}
}
