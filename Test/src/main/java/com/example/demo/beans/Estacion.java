package com.example.demo.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "estaciones")
@Entity
public class Estacion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 4, unique = true)
	private String codigo;
	@Column(length = 30, unique = true)
	private String nombre;
	private Boolean estado;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Troncal troncal;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ruta> rutas;

	public Estacion(long id, String codigo, String nombre, Boolean estado, Troncal troncal, List<Ruta> rutas) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.estado = estado;
		this.troncal = troncal;
		this.rutas = rutas;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Troncal getTroncal() {
		return troncal;
	}

	public void setTroncal(Troncal troncal) {
		this.troncal = troncal;
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}

}
