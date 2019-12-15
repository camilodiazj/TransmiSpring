package com.sophos.transmilenio.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Component;

import com.sophos.transmilenio.beans.Estacion;

@Component
public class TroncalesPs {
	
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Estacion> encontrarEstacion(String id_troncal){
		StoredProcedureQuery sql = this.em.createNamedStoredProcedureQuery("sp_estacion_por_troncal");
		sql.setParameter("id_troncal", id_troncal);	
		sql.execute();
		return sql.getResultList();
	}
	

	
}
