package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SectorParaje database table.
 * 
 */
@Entity
@NamedQuery(name="SectorParaje.findAll", query="SELECT s FROM SectorParaje s")
public class SectorParaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CodigoSector")
	private String codigoSector;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="Estatus")
	private String estatus;

	@Column(name="Oficio")
	private BigDecimal oficio;

	@Column(name="RegID")
	private String regID;

	//bi-directional many-to-one association to Recinto
	@OneToMany(mappedBy="sectorParaje")
	private List<Recinto> recintos;

	//bi-directional many-to-one association to CiudadSeccion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCiudadSeccion", referencedColumnName="ID")
	private CiudadSeccion ciudadSeccion;

	public SectorParaje() {
	}

	public String getCodigoSector() {
		return this.codigoSector;
	}

	public void setCodigoSector(String codigoSector) {
		this.codigoSector = codigoSector;
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

	public List<Recinto> getRecintos() {
		return this.recintos;
	}

	public void setRecintos(List<Recinto> recintos) {
		this.recintos = recintos;
	}

	public Recinto addRecinto(Recinto recinto) {
		getRecintos().add(recinto);
		recinto.setSectorParaje(this);

		return recinto;
	}

	public Recinto removeRecinto(Recinto recinto) {
		getRecintos().remove(recinto);
		recinto.setSectorParaje(null);

		return recinto;
	}

	public CiudadSeccion getCiudadSeccion() {
		return this.ciudadSeccion;
	}

	public void setCiudadSeccion(CiudadSeccion ciudadSeccion) {
		this.ciudadSeccion = ciudadSeccion;
	}

}