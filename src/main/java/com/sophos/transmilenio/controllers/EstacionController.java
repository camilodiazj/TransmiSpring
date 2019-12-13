	package com.sophos.transmilenio.controllers;
import java.util.ArrayList;
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
import com.sophos.transmilenio.beans.Ruta;
import com.sophos.transmilenio.beans.Troncal;
import com.sophos.transmilenio.daos.EstacionDao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(
		value="API CRUD Estacion",
		protocols = "HTTP/REST",
		consumes = "INTERNO"
		)

@CrossOrigin
@RestController
@RequestMapping("/estaciones")
public class EstacionController{
	
	@Autowired
	EntityManager em;
		
	@Autowired
	EntityManagerFactory fabrica;
	
	@Autowired
	private EstacionDao dao;
	
	
	@ApiOperation(value = "Consulta de rutas por estación", response = Iterable.class, httpMethod = "GET")
	@GetMapping("/{codigo}/rutas")
	public ResponseEntity<List<Ruta>> getEstacion2(@PathVariable String codigo){
		   TypedQuery<Ruta> query
		      = em.createQuery("SELECT d FROM Estacion e INNER JOIN e.rutas d WHERE e.codEstacion = '"+codigo+"'", Ruta.class);
		    List<Ruta> resultList = query.getResultList();
		return new ResponseEntity<List<Ruta>>(resultList,HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Consulta de estaciones", response = Iterable.class, httpMethod = "GET")
	@GetMapping("")
	public ResponseEntity<Iterable<Estacion>> verTruncal(){
		if(dao.count()>0) {
			return new ResponseEntity<Iterable<Estacion>>(dao.findAll(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Iterable<Estacion>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Consulta de estaciones por codigo de estación", response = Estacion.class, httpMethod = "GET")
	@GetMapping("/{id}")
	public ResponseEntity<Estacion> verEstacionId(@PathVariable String id){
		Optional<Estacion> estacion = dao.findById(id);
		if(estacion.isPresent()) {
			return new ResponseEntity<Estacion>(estacion.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Estacion>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@ApiOperation(value = "Registro de estacion", response = String.class, httpMethod = "POST")
	@PostMapping("")
	@Transactional
	public  ResponseEntity<String> addEstacion2(@RequestBody Estacion estacion) {
	    try {
	    	em.createNativeQuery("INSERT INTO Estacion (codEstacion, nombre, estado, horaApertura, horaCierre, codTroncal) VALUES (?,?,?,?,?,?)")
		      .setParameter(1, estacion.getCodEstacion())
		      .setParameter(2, estacion.getNombre())
		      .setParameter(3, estacion.getEstado())
		      .setParameter(4, estacion.getHoraApertura())
		      .setParameter(5, estacion.getHoraCierre())
		      .setParameter(6, estacion.getCodTroncal())
		      .executeUpdate();
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	    return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Borrado de estación por id", response = String.class, httpMethod = "DELETE")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEstacion(@PathVariable String id) {
		try {
			dao.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("No se pudo eliminar el registro",HttpStatus.BAD_REQUEST);
		}
		    return new ResponseEntity<String>("Registro Eliminado",HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar estacion", response = String.class, httpMethod = "PUT")
	@PutMapping("/")
	@Transactional       
	public  ResponseEntity<String> actualizarEstacion(@RequestBody Estacion estacion) {
	    try {          //Update Estacion set nombre = "Terminall", estado="Cerrada", horaApertura="05:00:00", horaCierre="08:00:00", codTroncal = "C" where codEstacion = "B1";
	    	em.createNativeQuery("UPDATE Estacion SET nombre = ?, estado = ?, horaApertura = ?, horaCierre = ?, codTroncal = ? WHERE codEstacion = ? ")
		      .setParameter(1, estacion.getNombre())
		      .setParameter(2, estacion.getEstado())
		      .setParameter(3, estacion.getHoraApertura())
		      .setParameter(4, estacion.getHoraCierre())
		      .setParameter(5, estacion.getCodTroncal())
		      .setParameter(6, estacion.getCodEstacion())
		      .executeUpdate();
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	    return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	
}
