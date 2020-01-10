package com.example.demo.daos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.beans.Ruta;

@Repository
public interface IRutaDao extends CrudRepository<Ruta, Long>{
	public Optional<Ruta> findByNombre(String nombre);
	public Optional<Ruta> findByCodigo(String codigo); 
}
