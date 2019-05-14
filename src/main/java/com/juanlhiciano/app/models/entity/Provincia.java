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

	@Column(name="Estatus")
	private String estatus;

	@Column(name="Oficio")
	private BigDecimal oficio;

	@Column(name="RegID")
	private String regID;

	@Column(name="ZONA")
	private String zona;

	//bi-directional many-to-one association to Circunscripcion
	@OneToMany(mappedBy="provincia")
	private List<Circunscripcion> circunscripcions;

	//bi-directional many-to-one association to Municipio
	@OneToMany(mappedBy="provincia1")
	private List<Municipio> municipios;

	//bi-directional one-to-one association to Municipio
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMunicipioCabecera", referencedColumnName="ID")
	private Municipio municipio;

	//bi-directional many-to-one association to Region
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Region", referencedColumnName="ID")
	private Region regionBean;

	public Provincia() {
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
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

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public List<Circunscripcion> getCircunscripcions() {
		return this.circunscripcions;
	}

	public void setCircunscripcions(List<Circunscripcion> circunscripcions) {
		this.circunscripcions = circunscripcions;
	}

	public Circunscripcion addCircunscripcion(Circunscripcion circunscripcion) {
		getCircunscripcions().add(circunscripcion);
		circunscripcion.setProvincia(this);

		return circunscripcion;
	}

	public Circunscripcion removeCircunscripcion(Circunscripcion circunscripcion) {
		getCircunscripcions().remove(circunscripcion);
		circunscripcion.setProvincia(null);

		return circunscripcion;
	}

	public List<Municipio> getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public Municipio addMunicipio(Municipio municipio) {
		getMunicipios().add(municipio);
		municipio.setProvincia1(this);

		return municipio;
	}

	public Municipio removeMunicipio(Municipio municipio) {
		getMunicipios().remove(municipio);
		municipio.setProvincia1(null);

		return municipio;
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Region getRegionBean() {
		return this.regionBean;
	}

	public void setRegionBean(Region regionBean) {
		this.regionBean = regionBean;
	}

}