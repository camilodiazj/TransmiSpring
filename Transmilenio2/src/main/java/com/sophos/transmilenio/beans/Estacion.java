package com.sophos.transmilenio.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity

//@Table(name="Estacion", schema="transmi2")
@Table(name="Estacion")

@JsonIgnoreProperties(value= {"troncal","rutas"})
public class Estacion implements Serializable {	
	
	
	@GeneratedValue
	@Column(name="idEstacion", nullable=false, length=11, unique=true) 
	private int idEstacion;
	
	@Id	
	@Column(name="codEstacion", nullable=false, length=3, unique=true)
	private String codEstacion;
	
	@Column(name="nombre", nullable=false, length=45)
	private String nombre;
	
	@Column(name="estado", nullable=false, length=45)
	private String estado;
	
	//@Column(name="codTroncal", nullable=false, length=3)
	//private String codTroncal;
	@JsonFormat(timezone = "UTC", pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	@Column(name="horaApertura", nullable=false)
	private Date horaApertura;
	@JsonFormat(timezone = "UTC", pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	@Column(name="horaCierre")
	private Date horaCierre;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="codTroncal", referencedColumnName = "codTroncal")
	private Troncal troncal;
	
	@Column(name="codTroncal", nullable=false, length=45,insertable = false, updatable = false)
	String codTroncal;
	
	@JoinTable(
			name = "EstacionRuta",
			joinColumns = @JoinColumn(name = "codEstacion", nullable = false),
			inverseJoinColumns = @JoinColumn(name="codRuta", nullable = false)	
	)	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Ruta> rutas;
	
	@JsonIgnoreProperties(value= {"troncal"})
	public Estacion(String codEstacion, String nombre, String estado, Date horaApertura, Date horaCierre,
			Troncal troncal) {
		this.codEstacion = codEstacion;
		this.nombre = nombre;
		this.estado = estado;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.troncal = troncal;
	}

	public Estacion() {
		
	}
	
	
	public String getCodTroncal() {
		return codTroncal;
	}

	public void setCodTroncal(String codTroncal) {
		this.codTroncal = codTroncal;
	}

	public Set<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(Set<Ruta> rutas) {
		this.rutas = rutas;
	}

	public String getCodEstacion() {
		return codEstacion;
	}

	public void setCodEstacion(String codEstacion) {
		this.codEstacion = codEstacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(Date horaApertura) {
		this.horaApertura = horaApertura;
	}

	public Date getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(Date horaCierre) {
		this.horaCierre = horaCierre;
	}

	public Troncal getTroncal() {
		return troncal;
	}

	public void setTroncal(Troncal troncal) {
		this.troncal = troncal;
	}
	
	}


