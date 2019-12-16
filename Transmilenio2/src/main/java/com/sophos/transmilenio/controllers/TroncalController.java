package com.sophos.transmilenio.controllers;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
import com.sophos.transmilenio.beans.Troncal;
import com.sophos.transmilenio.daos.TroncalDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(
		value="API CRUD Troncal",
		protocols = "HTTP/REST",
		consumes = "INTERNO"
		)
@CrossOrigin
@RestController
@RequestMapping("/troncales")
public class TroncalController {

	@Autowired
	EntityManager em;
	
	@Autowired
	EntityManagerFactory fabrica;
	
	@Autowired
	private TroncalDao dao;	
	
	@ApiOperation(value = "Actualizar troncal", response = Iterable.class, httpMethod = "PUT")
	@PutMapping("/")
	@Transactional       
	public ResponseEntity<String> actualizarTroncal(@RequestBody Troncal troncal) {
		if (troncal.getNombre().length()>0&&troncal.getCodTroncal().length()==1) {
	    	try {          //Update Troncal set nombre = "Av.26" where codTroncal = "K";
		    	em.createNativeQuery("UPDATE Troncal SET nombre = ? WHERE codTroncal = ? ")
			      .setParameter(1, troncal.getNombre())
			      .setParameter(2, troncal.getCodTroncal())
			      .executeUpdate();
			} catch (Exception e) {
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
	    	return new ResponseEntity<String>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Consulta de estaciones por troncal", response = Iterable.class, httpMethod = "GET")
	@GetMapping("/{codigo}/estaciones")
	public ResponseEntity<List<Estacion>> getEstacion2(@PathVariable String codigo){
		   TypedQuery<Estacion> query
		      = em.createQuery("SELECT d FROM Troncal e INNER JOIN e.estaciones d WHERE e.codTroncal = '"+codigo+"'", Estacion.class);
		    List<Estacion> resultList = query.getResultList();
		return new ResponseEntity<List<Estacion>>(resultList,HttpStatus.OK);
	}
		
	@ApiOperation(value = "Consulta de troncales", response = Iterable.class, httpMethod = "GET")
	@GetMapping("")
	public ResponseEntity<Iterable<Troncal>> verTroncal(){
		if(dao.count()>0) {
			return new ResponseEntity<Iterable<Troncal>>(dao.findAll(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Iterable<Troncal>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Consulta de troncales por id", response = Troncal.class, httpMethod = "GET")
	@GetMapping("/{id}")
	public ResponseEntity<Troncal> verEstacionId(@PathVariable String id){
		Optional<Troncal> troncal = dao.findById(id);
		if(troncal.isPresent()) {
			return new ResponseEntity<Troncal>(troncal.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Troncal>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Registro de troncales", response = String.class, httpMethod = "POST")
	@PostMapping("")
	public ResponseEntity<String> addTroncal2(@RequestBody Troncal troncal) {
		List<Troncal> troncales = (List<Troncal>) dao.findAll(); 
		boolean existe = false;
		for (int i = 0; i < troncales.size()&&!existe; i++) {
			if(troncales.get(i).getCodTroncal().equals(troncal.getCodTroncal())) {
				existe = true;
				System.out.println("La troncal ya existe.");
			}
		}
		if(!troncal.getCodTroncal().isEmpty()&&!troncal.getNombre().isEmpty()&&!existe) {
			dao.save(troncal);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}   
	}
	
	@ApiOperation(value = "Borrado de troncal por id", response = String.class, httpMethod = "DELETE")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTroncal(@PathVariable String id) {
		try {
			dao.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("No se pudo eliminar el registro",HttpStatus.BAD_REQUEST);
		}
		    return new ResponseEntity<String>("Registro Eliminado",HttpStatus.OK);
	}
	
	
}
