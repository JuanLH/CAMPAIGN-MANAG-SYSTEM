package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Ocupacion database table.
 * 
 */
@Entity
@NamedQuery(name="Ocupacion.findAll", query="SELECT o FROM Ocupacion o")
public class Ocupacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="Estatus")
	private String estatus;

	@Column(name="RegID")
	private String regID;

	@Column(name="RequiereTitulo")
	private String requiereTitulo;

	//bi-directional many-to-one association to Padron2020
	@OneToMany(mappedBy="ocupacion")
	private List<Padron2020> padron2020s;

	public Ocupacion() {
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

	public String getRegID() {
		return this.regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

	public String getRequiereTitulo() {
		return this.requiereTitulo;
	}

	public void setRequiereTitulo(String requiereTitulo) {
		this.requiereTitulo = requiereTitulo;
	}

	public List<Padron2020> getPadron2020s() {
		return this.padron2020s;
	}

	public void setPadron2020s(List<Padron2020> padron2020s) {
		this.padron2020s = padron2020s;
	}

	public Padron2020 addPadron2020(Padron2020 padron2020) {
		getPadron2020s().add(padron2020);
		padron2020.setOcupacion(this);

		return padron2020;
	}

	public Padron2020 removePadron2020(Padron2020 padron2020) {
		getPadron2020s().remove(padron2020);
		padron2020.setOcupacion(null);

		return padron2020;
	}

}