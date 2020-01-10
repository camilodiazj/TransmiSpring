package com.example.demo.daos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.beans.Troncal;

@Repository
public interface ITroncalDao extends CrudRepository<Troncal, Long>{
	
	public Optional<Troncal> findByCodigo(String codigo);
		
}
