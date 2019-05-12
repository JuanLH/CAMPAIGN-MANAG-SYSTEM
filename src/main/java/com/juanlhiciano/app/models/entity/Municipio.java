package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the Municipio database table.
 * 
 */
@Entity
@NamedQuery(name="Municipio.findAll", query="SELECT m FROM Municipio m")
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="DM")
	private String dm;

	@Column(name="Estatus")
	private String estatus;

	@Column(name="FechaCreacion")
	private Timestamp fechaCreacion;

	@Column(name="FechaModificacion")
	private Timestamp fechaModificacion;

	@Column(name="ID")
	private short id;

	private short IDMunicipioPadre;

	@Column(name="IdUsuarioCreacion")
	private int idUsuarioCreacion;

	@Column(name="IdUsuarioModificacion")
	private int idUsuarioModificacion;

	@Column(name="Oficio")
	private BigDecimal oficio;

	@Column(name="RegID")
	private String regID;

	//bi-directional many-to-one association to Provincia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDProvincia", referencedColumnName="ID")
	private Provincia provincia;

	public Municipio() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDm() {
		return this.dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
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

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public short getIDMunicipioPadre() {
		return this.IDMunicipioPadre;
	}

	public void setIDMunicipioPadre(short IDMunicipioPadre) {
		this.IDMunicipioPadre = IDMunicipioPadre;
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

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}