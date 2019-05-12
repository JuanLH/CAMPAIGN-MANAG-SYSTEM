package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the Provincia database table.
 * 
 */
@Entity
@NamedQuery(name="Provincia.findAll", query="SELECT p FROM Provincia p")
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="Estatus")
	private String estatus;

	private short IDMunicipioCabecera;

	@Column(name="Oficio")
	private BigDecimal oficio;

	@Column(name="RegID")
	private String regID;

	@Column(name="Region")
	private int region;

	@Column(name="ZONA")
	private String zona;

	//bi-directional many-to-one association to Municipio
	@OneToMany(mappedBy="provincia")
	private List<Municipio> municipios;

	public Provincia() {
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

	public short getIDMunicipioCabecera() {
		return this.IDMunicipioCabecera;
	}

	public void setIDMunicipioCabecera(short IDMunicipioCabecera) {
		this.IDMunicipioCabecera = IDMunicipioCabecera;
	}

	public BigDecimal getOficio() {
		return this.oficio;
	}

	public void setOficio(BigDecimal oficio) {
		this.oficio = oficio;
	}

	public String getRegID() {
		return this.regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

	public int getRegion() {
		return this.region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public List<Municipio> getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public Municipio addMunicipio(Municipio municipio) {
		getMunicipios().add(municipio);
		municipio.setProvincia(this);

		return municipio;
	}

	public Municipio removeMunicipio(Municipio municipio) {
		getMunicipios().remove(municipio);
		municipio.setProvincia(null);

		return municipio;
	}

}