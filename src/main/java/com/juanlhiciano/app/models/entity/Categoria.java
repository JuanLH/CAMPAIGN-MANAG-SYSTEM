package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Categoria database table.
 * 
 */
@Entity
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="LlevaColegio")
	private String llevaColegio;

	@Column(name="LlevaDatosActa")
	private String llevaDatosActa;

	@Column(name="LlevaDatosPasaporte")
	private String llevaDatosPasaporte;

	@Column(name="PuedeVotar")
	private String puedeVotar;

	@Column(name="RegID")
	private String regID;

	//bi-directional many-to-one association to Padron2020
	@OneToMany(mappedBy="categoria")
	private List<Padron2020> padron2020s;

	public Categoria() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLlevaColegio() {
		return this.llevaColegio;
	}

	public void setLlevaColegio(String llevaColegio) {
		this.llevaColegio = llevaColegio;
	}

	public String getLlevaDatosActa() {
		return this.llevaDatosActa;
	}

	public void setLlevaDatosActa(String llevaDatosActa) {
		this.llevaDatosActa = llevaDatosActa;
	}

	public String getLlevaDatosPasaporte() {
		return this.llevaDatosPasaporte;
	}

	public void setLlevaDatosPasaporte(String llevaDatosPasaporte) {
		this.llevaDatosPasaporte = llevaDatosPasaporte;
	}

	public String getPuedeVotar() {
		return this.puedeVotar;
	}

	public void setPuedeVotar(String puedeVotar) {
		this.puedeVotar = puedeVotar;
	}

	public String getRegID() {
		return this.regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

	public List<Padron2020> getPadron2020s() {
		return this.padron2020s;
	}

	public void setPadron2020s(List<Padron2020> padron2020s) {
		this.padron2020s = padron2020s;
	}

	public Padron2020 addPadron2020(Padron2020 padron2020) {
		getPadron2020s().add(padron2020);
		padron2020.setCategoria(this);

		return padron2020;
	}

	public Padron2020 removePadron2020(Padron2020 padron2020) {
		getPadron2020s().remove(padron2020);
		padron2020.setCategoria(null);

		return padron2020;
	}

}