package com.example.demo.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "troncales")
public class Troncal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7027858417362912150L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 1,unique = true)
	private String codigo;
	
	@OneToMany(mappedBy = "troncal")
	private List<Estacion> estaciones;
	
	public Troncal(long id, String codigo, List<Estacion> estaciones) {
		this.id = id;
		this.codigo = codigo;
		this.estaciones = estaciones;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Estacion> getEstaciones() {
		return estaciones;
	}

	public void setEstaciones(List<Estacion> estaciones) {
		this.estaciones = estaciones;
	}

}
