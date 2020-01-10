package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Troncal;
import com.example.demo.daos.ITroncalDao;

@Service
public class TroncalService{
	
	@Autowired
	private ITroncalDao troncalDao;
	//CREATE
	public Troncal create(Troncal troncal) {
 		if(troncalDao.findByCodigo(troncal.getCodigo()).isPresent()) {
 			return null;
 		}else {
 			troncalDao.save(troncal);
 			return troncal;	
 		}
	}
	//READ
	public List<Troncal> getAll(){
		return (List<Troncal>) troncalDao.findAll();
	}
	//UPDATE
	public Troncal update(Troncal troncal) {
		
		Optional<Troncal> t = troncalDao.findByCodigo(troncal.getCodigo());
		
		if(t.isPresent()) {
			troncal.setId(t.get().getId());
			troncalDao.save(troncal);
			return troncal;
 		}else {
 			return null;	
 		}
	}
	//DELETE 
	public boolean delete(Troncal troncal) {
		if(troncalDao.findById(troncal.getId()).isPresent()){
			troncalDao.delete(troncal);
			return true;
		}else {
			return false;
		}
	}
	
	
}
