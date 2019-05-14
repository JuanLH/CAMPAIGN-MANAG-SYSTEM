package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


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

	@Column(name="IdUsuarioCreacion")
	private int idUsuarioCreacion;

	@Column(name="IdUsuarioModificacion")
	private int idUsuarioModificacion;

	@Column(name="Oficio")
	private BigDecimal oficio;

	@Column(name="RegID")
	private String regID;

	//bi-directional many-to-one association to CiudadSeccion
	@OneToMany(mappedBy="municipio1")
	private List<CiudadSeccion> ciudadSeccions1;

	//bi-directional many-to-one association to CiudadSeccion
	@OneToMany(mappedBy="municipio2")
	private List<CiudadSeccion> ciudadSeccions2;

	//bi-directional many-to-one association to Colegio
	@OneToMany(mappedBy="municipio")
	private List<Colegio> colegios;

	//bi-directional many-to-one association to Provincia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDProvincia", referencedColumnName="ID")
	private Provincia provincia1;

	//bi-directional many-to-one association to Municipio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMunicipioPadre", referencedColumnName="ID")
	private Municipio municipio;

	//bi-directional many-to-one association to Municipio
	@OneToMany(mappedBy="municipio")
	private List<Municipio> municipios;

	//bi-directional many-to-one association to Padron2020
	@OneToMany(mappedBy="municipio")
	private List<Padron2020> padron2020s;

	//bi-directional one-to-one association to Provincia
	@OneToOne(mappedBy="municipio", fetch=FetchType.LAZY)
	private Provincia provincia2;

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

	public List<CiudadSeccion> getCiudadSeccions1() {
		return this.ciudadSeccions1;
	}

	public void setCiudadSeccions1(List<CiudadSeccion> ciudadSeccions1) {
		this.ciudadSeccions1 = ciudadSeccions1;
	}

	public CiudadSeccion addCiudadSeccions1(CiudadSeccion ciudadSeccions1) {
		getCiudadSeccions1().add(ciudadSeccions1);
		ciudadSeccions1.setMunicipio1(this);

		return ciudadSeccions1;
	}

	public CiudadSeccion removeCiudadSeccions1(CiudadSeccion ciudadSeccions1) {
		getCiudadSeccions1().remove(ciudadSeccions1);
		ciudadSeccions1.setMunicipio1(null);

		return ciudadSeccions1;
	}

	public List<CiudadSeccion> getCiudadSeccions2() {
		return this.ciudadSeccions2;
	}

	public void setCiudadSeccions2(List<CiudadSeccion> ciudadSeccions2) {
		this.ciudadSeccions2 = ciudadSeccions2;
	}

	public CiudadSeccion addCiudadSeccions2(CiudadSeccion ciudadSeccions2) {
		getCiudadSeccions2().add(ciudadSeccions2);
		ciudadSeccions2.setMunicipio2(this);

		return ciudadSeccions2;
	}

	public CiudadSeccion removeCiudadSeccions2(CiudadSeccion ciudadSeccions2) {
		getCiudadSeccions2().remove(ciudadSeccions2);
		ciudadSeccions2.setMunicipio2(null);

		return ciudadSeccions2;
	}

	public List<Colegio> getColegios() {
		return this.colegios;
	}

	public void setColegios(List<Colegio> colegios) {
		this.colegios = colegios;
	}

	public Colegio addColegio(Colegio colegio) {
		getColegios().add(colegio);
		colegio.setMunicipio(this);

		return colegio;
	}

	public Colegio removeColegio(Colegio colegio) {
		getColegios().remove(colegio);
		colegio.setMunicipio(null);

		return colegio;
	}

	public Provincia getProvincia1() {
		return this.provincia1;
	}

	public void setProvincia1(Provincia provincia1) {
		this.provincia1 = provincia1;
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public List<Municipio> getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public Municipio addMunicipio(Municipio municipio) {
		getMunicipios().add(municipio);
		municipio.setMunicipio(this);

		return municipio;
	}

	public Municipio removeMunicipio(Municipio municipio) {
		getMunicipios().remove(municipio);
		municipio.setMunicipio(null);

		return municipio;
	}

	public List<Padron2020> getPadron2020s() {
		return this.padron2020s;
	}

	public void setPadron2020s(List<Padron2020> padron2020s) {
		this.padron2020s = padron2020s;
	}

	public Padron2020 addPadron2020(Padron2020 padron2020) {
		getPadron2020s().add(padron2020);
		padron2020.setMunicipio(this);

		return padron2020;
	}

	public Padron2020 removePadron2020(Padron2020 padron2020) {
		getPadron2020s().remove(padron2020);
		padron2020.setMunicipio(null);

		return padron2020;
	}

	public Provincia getProvincia2() {
		return this.provincia2;
	}

	public void setProvincia2(Provincia provincia2) {
		this.provincia2 = provincia2;
	}

}