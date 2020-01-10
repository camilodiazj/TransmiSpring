package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Estacion;
import com.example.demo.daos.IEstacionDao;

@Service
public class EstacionService {

	@Autowired
	IEstacionDao estacionDao;

	// CREATE
	public Estacion create(Estacion estacion) {
		if (estacionDao.findByCodigo(estacion.getCodigo()).isPresent()) {
			return null;
		} else {
			estacionDao.save(estacion);
			return estacion;
		}
	}

	// READ
	public List<Estacion> getAll() {
		return (List<Estacion>) estacionDao.findAll();
	}

	// UPDATE
	public Estacion update(Estacion estacion) {

		Optional<Estacion> t = estacionDao.findByCodigo(estacion.getCodigo());

		if (t.isPresent()) {
			estacion.setId(t.get().getId());
			estacionDao.save(estacion);
			return estacion;
		} else {
			return null;
		}
	}

	// DELETE
	public boolean delete(Estacion estacion) {
		if (estacionDao.findById(estacion.getId()).isPresent()) {
			estacionDao.delete(estacion);
			return true;
		} else {
			return false;
		}
	}

}
