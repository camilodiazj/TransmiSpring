package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Ruta;
import com.example.demo.daos.IRutaDao;

@Service
public class RutaService {
	@Autowired
	IRutaDao rutaDao;

	// CREATE
	public Ruta create(Ruta ruta) {
		if (rutaDao.findByCodigo(ruta.getCodigo()).isPresent()) {
			return null;
		} else {
			rutaDao.save(ruta);
			return ruta;
		}
	}

	// READ
	public List<Ruta> getAll() {
		return (List<Ruta>) rutaDao.findAll();
	}

	// UPDATE
	public Ruta update(Ruta ruta) {

		Optional<Ruta> t = rutaDao.findByCodigo(ruta.getCodigo());

		if (t.isPresent()) {
			ruta.setId(t.get().getId());
			rutaDao.save(ruta);
			return ruta;
		} else {
			return null;
		}
	}

	// DELETE
	public boolean delete(Ruta ruta) {
		if (rutaDao.findById(ruta.getId()).isPresent()) {
			rutaDao.delete(ruta);
			return true;
		} else {
			return false;
		}
	}

}
