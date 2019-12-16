package com.sophos.transmilenio.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(name="Ruta")
@JsonIgnoreProperties(value= {"estaciones"})
public class Ruta implements Serializable {
	
	@GeneratedValue
	@Column(name="idRuta", nullable=false, length=11, unique=true)
	private int idRuta;
	@Id
	@Column(name="codRuta", nullable=false, length=3, unique=true)
	private String codRuta;
	@Column(name="nombre", nullable=false, length=45, unique=true)
	private String nombre;
	@JsonFormat(timezone = "UTC", pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	@Column(name="inicioOperacion")
	private Date inicioOperacion;
	@JsonFormat(timezone = "UTC", pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	@Column(name="finOperacion")
	private Date finOperacion;
	
	
	@ManyToMany(mappedBy = "rutas")
	private Set<Estacion> estaciones;
	
	public Ruta() {
	}

	
	public Set<Estacion> getEstaciones() {
		return estaciones;
	}


	public void setEstaciones(Set<Estacion> estaciones) {
		this.estaciones = estaciones;
	}


	public String getCodRuta() {
		return codRuta;
	}

	public void setCodRuta(String codRuta) {
		this.codRuta = codRuta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getInicioOperacion() {
		return inicioOperacion;
	}

	public void setInicioOperacion(Date inicioOperacion) {
		this.inicioOperacion = inicioOperacion;
	}

	public Date getFinOperacion() {
		return finOperacion;
	}

	public void setFinOperacion(Date finOperacion) {
		this.finOperacion = finOperacion;
	}
	
}
