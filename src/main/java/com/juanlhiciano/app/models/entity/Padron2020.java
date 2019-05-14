package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import java.sql.Timestamp;


/**
 * The persistent class for the padron2020 database table.
 * 
 */
@Entity
@Table(name="padron2020")
@NamedQuery(name="Padron2020.findAll", query="SELECT p FROM Padron2020 p")
public class Padron2020 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Apellido1")
	private String apellido1;

	@Column(name="Apellido2")
	private String apellido2;
	
	@Id
	@Pattern(regexp="(^$|[0-9]{11})")
	@NotEmpty
	@Column(name="Cedula")
	private String cedula;

	@Column(name="CedulaAnterior")
	private String cedulaAnterior;

	@Column(name="EstatusCedulaAzul")
	private String estatusCedulaAzul;

	@Column(name="FechaNacimiento")
	private Timestamp fechaNacimiento;

	@Column(name="IdEstadoCivil")
	private String idEstadoCivil;

	private String IDEstatus;

	@Column(name="IdSexo")
	private String idSexo;

	@Column(name="LugarNacimiento")
	private String lugarNacimiento;

	@Column(name="mun_ced")
	private String munCed;

	@Column(name="Nombres")
	private String nombres;

	@Column(name="seq_ced")
	private String seqCed;

	@Column(name="ver_ced")
	private String verCed;

	//bi-directional many-to-one association to Categoria
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCategoria", referencedColumnName="ID")
	private Categoria categoria;

	//bi-directional many-to-one association to Ocupacion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdOcupacion", referencedColumnName="ID")
	private Ocupacion ocupacion;

	//bi-directional many-to-one association to Nacionalidad
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDNacionalidad", referencedColumnName="ID")
	private Nacionalidad nacionalidad;

	//bi-directional many-to-one association to Municipio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMunicipio", referencedColumnName="ID")
	private Municipio municipio;

	//bi-directional many-to-one association to Colegio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDColegio", referencedColumnName="IDColegio")
	private Colegio colegio;

	//bi-directional many-to-one association to CausaCancelacion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCausaCancelacion", referencedColumnName="ID")
	private CausaCancelacion causaCancelacion;

	public Padron2020() {
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

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCedulaAnterior() {
		return this.cedulaAnterior;
	}

	public void setCedulaAnterior(String cedulaAnterior) {
		this.cedulaAnterior = cedulaAnterior;
	}

	public String getEstatusCedulaAzul() {
		return this.estatusCedulaAzul;
	}

	public void setEstatusCedulaAzul(String estatusCedulaAzul) {
		this.estatusCedulaAzul = estatusCedulaAzul;
	}

	public Timestamp getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getIdEstadoCivil() {
		return this.idEstadoCivil;
	}

	public void setIdEstadoCivil(String idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public String getIDEstatus() {
		return this.IDEstatus;
	}

	public void setIDEstatus(String IDEstatus) {
		this.IDEstatus = IDEstatus;
	}

	public String getIdSexo() {
		return this.idSexo;
	}

	public void setIdSexo(String idSexo) {
		this.idSexo = idSexo;
	}

	public String getLugarNacimiento() {
		return this.lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
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

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Ocupacion getOcupacion() {
		return this.ocupacion;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}

	public Nacionalidad getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Colegio getColegio() {
		return this.colegio;
	}

	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}

	public CausaCancelacion getCausaCancelacion() {
		return this.causaCancelacion;
	}

	public void setCausaCancelacion(CausaCancelacion causaCancelacion) {
		this.causaCancelacion = causaCancelacion;
	}

}