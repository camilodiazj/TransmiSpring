package com.sophos.transmilenio.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@SuppressWarnings("serial")
@Entity


@Table(name="Troncal")
@JsonIgnoreProperties(value= {"estaciones"})
public class Troncal implements Serializable{
	
	
	@Column(name="idTroncal", length=11, unique=true, nullable=false)
	@GeneratedValue
	private int idTroncal;
	
	@Id 
	@Column(name="codTroncal", length=3)
	private String codTroncal;
	
	@Column(name="nombre", length=45)
	private String nombre;
	
	@OneToMany(mappedBy="troncal", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Estacion> estaciones;
	
	
	public Troncal() {
		this.estaciones = new HashSet<Estacion>();
	}
		
	public Set<Estacion> getEstaciones() {
		return estaciones;
	}

	public void setEstaciones(Set<Estacion> estaciones) {
		this.estaciones = estaciones;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getIdTroncal() {
		return idTroncal;
	}

	public void setIdTroncal(int idTroncal) {
		this.idTroncal = idTroncal;
	}

	public String getCodTroncal() {
		return codTroncal;
	}

	public void setCodTroncal(String codTroncal) {
		this.codTroncal = codTroncal;
	}
	
}

