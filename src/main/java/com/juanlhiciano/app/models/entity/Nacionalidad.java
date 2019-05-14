package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Nacionalidad database table.
 * 
 */
@Entity
@NamedQuery(name="Nacionalidad.findAll", query="SELECT n FROM Nacionalidad n")
public class Nacionalidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="Estatus")
	private String estatus;

	@Column(name="Gentilicio")
	private String gentilicio;

	@Column(name="RegID")
	private String regID;

	@Column(name="Siglas")
	private String siglas;

	//bi-directional many-to-one association to Padron2020
	@OneToMany(mappedBy="nacionalidad")
	private List<Padron2020> padron2020s;

	public Nacionalidad() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getGentilicio() {
		return this.gentilicio;
	}

	public void setGentilicio(String gentilicio) {
		this.gentilicio = gentilicio;
	}

	public String getRegID() {
		return this.regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

	public String getSiglas() {
		return this.siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public List<Padron2020> getPadron2020s() {
		return this.padron2020s;
	}

	public void setPadron2020s(List<Padron2020> padron2020s) {
		this.padron2020s = padron2020s;
	}

	public Padron2020 addPadron2020(Padron2020 padron2020) {
		getPadron2020s().add(padron2020);
		padron2020.setNacionalidad(this);

		return padron2020;
	}

	public Padron2020 removePadron2020(Padron2020 padron2020) {
		getPadron2020s().remove(padron2020);
		padron2020.setNacionalidad(null);

		return padron2020;
	}

}