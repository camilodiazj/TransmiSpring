package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Estacion;
import com.example.demo.services.EstacionService;

@RestController
public class EstacionController {
		
	@Autowired
	EstacionService estacionService; 
	
	@PostMapping("/")
	public ResponseEntity<Estacion> post(@RequestBody Estacion estacion){
		try {
			if(estacionService.create(estacion)==null) {
				return new ResponseEntity<Estacion>(HttpStatus.BAD_REQUEST);
			}else {
				return new ResponseEntity<Estacion>(estacion, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<Estacion>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
