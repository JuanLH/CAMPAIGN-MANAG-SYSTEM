package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Circunscripcion database table.
 * 
 */
@Entity
@NamedQuery(name="Circunscripcion.findAll", query="SELECT c FROM Circunscripcion c")
public class Circunscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CodigoCircunscripcion")
	private String codigoCircunscripcion;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="RegID")
	private String regID;

	//bi-directional many-to-one association to Provincia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDProvincia", referencedColumnName="ID")
	private Provincia provincia;

	//bi-directional many-to-one association to Recinto
	@OneToMany(mappedBy="circunscripcion")
	private List<Recinto> recintos;

	public Circunscripcion() {
	}

	public String getCodigoCircunscripcion() {
		return this.codigoCircunscripcion;
	}

	public void setCodigoCircunscripcion(String codigoCircunscripcion) {
		this.codigoCircunscripcion = codigoCircunscripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRegID() {
		return this.regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public List<Recinto> getRecintos() {
		return this.recintos;
	}

	public void setRecintos(List<Recinto> recintos) {
		this.recintos = recintos;
	}

	public Recinto addRecinto(Recinto recinto) {
		getRecintos().add(recinto);
		recinto.setCircunscripcion(this);

		return recinto;
	}

	public Recinto removeRecinto(Recinto recinto) {
		getRecintos().remove(recinto);
		recinto.setCircunscripcion(null);

		return recinto;
	}

}