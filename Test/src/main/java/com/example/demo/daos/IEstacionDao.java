package com.example.demo.daos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.beans.Estacion;

@Repository
public interface IEstacionDao extends CrudRepository<Estacion, Long>{
	public Optional<Estacion> findByCodigo(String codigo);
	public Optional<Estacion> findByNombre(String nombre);
	public Optional<Estacion> findByEstado(Boolean estado);
}
