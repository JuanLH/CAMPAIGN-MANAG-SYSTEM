package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the padron2k20 database table.
 * 
 */
@Entity
public class Padron2k20 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cedula;

	private String apellido1;

	private String apellido2;

	private String cedulaanterior;

	private String estatuscedulaazul;

	private Timestamp fechanacimiento;

	private Integer idcategoria;

	private String idcausacancelacion;

	private String idcolegio;

	private String idestadocivil;

	private String idestatus;

	private String idmunicipio;

	private Integer idnacionalidad;

	private Integer idocupacion;

	private String idsexo;

	private String lugarnacimiento;

	@Column(name="mun_ced")
	private String munCed;

	private String nombres;

	@Column(name="seq_ced")
	private String seqCed;

	@Column(name="ver_ced")
	private String verCed;

	public Padron2k20() {
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getCedulaanterior() {
		return this.cedulaanterior;
	}

	public void setCedulaanterior(String cedulaanterior) {
		this.cedulaanterior = cedulaanterior;
	}

	public String getEstatuscedulaazul() {
		return this.estatuscedulaazul;
	}

	public void setEstatuscedulaazul(String estatuscedulaazul) {
		this.estatuscedulaazul = estatuscedulaazul;
	}

	public Timestamp getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Timestamp fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public Integer getIdcategoria() {
		return this.idcategoria;
	}

	public void setIdcategoria(Integer idcategoria) {
		this.idcategoria = idcategoria;
	}

	public String getIdcausacancelacion() {
		return this.idcausacancelacion;
	}

	public void setIdcausacancelacion(String idcausacancelacion) {
		this.idcausacancelacion = idcausacancelacion;
	}

	public String getIdcolegio() {
		return this.idcolegio;
	}

	public void setIdcolegio(String idcolegio) {
		this.idcolegio = idcolegio;
	}

	public String getIdestadocivil() {
		return this.idestadocivil;
	}

	public void setIdestadocivil(String idestadocivil) {
		this.idestadocivil = idestadocivil;
	}

	public String getIdestatus() {
		return this.idestatus;
	}

	public void setIdestatus(String idestatus) {
		this.idestatus = idestatus;
	}

	public String getIdmunicipio() {
		return this.idmunicipio;
	}

	public void setIdmunicipio(String idmunicipio) {
		this.idmunicipio = idmunicipio;
	}

	public Integer getIdnacionalidad() {
		return this.idnacionalidad;
	}

	public void setIdnacionalidad(Integer idnacionalidad) {
		this.idnacionalidad = idnacionalidad;
	}

	public Integer getIdocupacion() {
		return this.idocupacion;
	}

	public void setIdocupacion(Integer idocupacion) {
		this.idocupacion = idocupacion;
	}

	public String getIdsexo() {
		return this.idsexo;
	}

	public void setIdsexo(String idsexo) {
		this.idsexo = idsexo;
	}

	public String getLugarnacimiento() {
		return this.lugarnacimiento;
	}

	public void setLugarnacimiento(String lugarnacimiento) {
		this.lugarnacimiento = lugarnacimiento;
	}

	public String getMunCed() {
		return this.munCed;
	}

	public void setMunCed(String munCed) {
		this.munCed = munCed;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getSeqCed() {
		return this.seqCed;
	}

	public void setSeqCed(String seqCed) {
		this.seqCed = seqCed;
	}

	public String getVerCed() {
		return this.verCed;
	}

	public void setVerCed(String verCed) {
		this.verCed = verCed;
	}

}