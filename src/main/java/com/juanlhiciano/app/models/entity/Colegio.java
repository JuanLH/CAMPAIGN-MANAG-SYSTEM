package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Colegio database table.
 * 
 */
@Entity
@NamedQuery(name="Colegio.findAll", query="SELECT c FROM Colegio c")
public class Colegio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CantidadInscritos")
	private int cantidadInscritos;

	@Column(name="CantidadReservada")
	private int cantidadReservada;

	@Column(name="CodigoColegio")
	private String codigoColegio;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="RegID")
	private String regID;

	@Column(name="TieneCupo")
	private String tieneCupo;

	//bi-directional many-to-one association to Municipio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMunicipio", referencedColumnName="ID")
	private Municipio municipio;

	//bi-directional many-to-one association to Recinto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDRecinto", referencedColumnName="ID")
	private Recinto recinto;

	//bi-directional many-to-one association to Padron2020
	@OneToMany(mappedBy="colegio")
	private List<Padron2020> padron2020s;

	public Colegio() {
	}

	public int getCantidadInscritos() {
		return this.cantidadInscritos;
	}

	public void setCantidadInscritos(int cantidadInscritos) {
		this.cantidadInscritos = cantidadInscritos;
	}

	public int getCantidadReservada() {
		return this.cantidadReservada;
	}

	public void setCantidadReservada(int cantidadReservada) {
		this.cantidadReservada = cantidadReservada;
	}

	public String getCodigoColegio() {
		return this.codigoColegio;
	}

	public void setCodigoColegio(String codigoColegio) {
		this.codigoColegio = codigoColegio;
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

	public String getTieneCupo() {
		return this.tieneCupo;
	}

	public void setTieneCupo(String tieneCupo) {
		this.tieneCupo = tieneCupo;
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Recinto getRecinto() {
		return this.recinto;
	}

	public void setRecinto(Recinto recinto) {
		this.recinto = recinto;
	}

	public List<Padron2020> getPadron2020s() {
		return this.padron2020s;
	}

	public void setPadron2020s(List<Padron2020> padron2020s) {
		this.padron2020s = padron2020s;
	}

	public Padron2020 addPadron2020(Padron2020 padron2020) {
		getPadron2020s().add(padron2020);
		padron2020.setColegio(this);

		return padron2020;
	}

	public Padron2020 removePadron2020(Padron2020 padron2020) {
		getPadron2020s().remove(padron2020);
		padron2020.setColegio(null);

		return padron2020;
	}

}