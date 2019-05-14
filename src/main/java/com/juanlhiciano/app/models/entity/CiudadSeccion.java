package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the CiudadSeccion database table.
 * 
 */
@Entity
@NamedQuery(name="CiudadSeccion.findAll", query="SELECT c FROM CiudadSeccion c")
public class CiudadSeccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CodigoCiudad")
	private String codigoCiudad;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="Estatus")
	private String estatus;

	@Column(name="FechaCreacion")
	private Timestamp fechaCreacion;

	@Column(name="FechaModificacion")
	private Timestamp fechaModificacion;

	@Column(name="IdUsuarioCreacion")
	private int idUsuarioCreacion;

	@Column(name="IdUsuarioModificacion")
	private int idUsuarioModificacion;

	@Column(name="Oficio")
	private long oficio;

	@Column(name="RegID")
	private String regID;

	//bi-directional many-to-one association to Municipio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMunicipio", referencedColumnName="ID")
	private Municipio municipio1;

	//bi-directional many-to-one association to Municipio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDDistritoMunicipal", referencedColumnName="ID")
	private Municipio municipio2;

	//bi-directional many-to-one association to SectorParaje
	@OneToMany(mappedBy="ciudadSeccion")
	private List<SectorParaje> sectorParajes;

	public CiudadSeccion() {
	}

	public String getCodigoCiudad() {
		return this.codigoCiudad;
	}

	public void setCodigoCiudad(String codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
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

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int getIdUsuarioCreacion() {
		return this.idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(int idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}

	public int getIdUsuarioModificacion() {
		return this.idUsuarioModificacion;
	}

	public void setIdUsuarioModificacion(int idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

	public long getOficio() {
		return this.oficio;
	}

	public void setOficio(long oficio) {
		this.oficio = oficio;
	}

	public String getRegID() {
		return this.regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

	public Municipio getMunicipio1() {
		return this.municipio1;
	}

	public void setMunicipio1(Municipio municipio1) {
		this.municipio1 = municipio1;
	}

	public Municipio getMunicipio2() {
		return this.municipio2;
	}

	public void setMunicipio2(Municipio municipio2) {
		this.municipio2 = municipio2;
	}

	public List<SectorParaje> getSectorParajes() {
		return this.sectorParajes;
	}

	public void setSectorParajes(List<SectorParaje> sectorParajes) {
		this.sectorParajes = sectorParajes;
	}

	public SectorParaje addSectorParaje(SectorParaje sectorParaje) {
		getSectorParajes().add(sectorParaje);
		sectorParaje.setCiudadSeccion(this);

		return sectorParaje;
	}

	public SectorParaje removeSectorParaje(SectorParaje sectorParaje) {
		getSectorParajes().remove(sectorParaje);
		sectorParaje.setCiudadSeccion(null);

		return sectorParaje;
	}

}