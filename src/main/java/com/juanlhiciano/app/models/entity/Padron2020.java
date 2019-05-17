package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@Size(max=10)
	private String cedulaAnterior;

	@Column(name="EstatusCedulaAzul")
	private char estatusCedulaAzul;

	@Column(name="FechaNacimiento")
	private Timestamp fechaNacimiento;

	private short IDCategoria;

	@Column(name="IDCausaCancelacion")
	private Integer idcausa_cancelacion;

	private int IDColegio;

	@Column(name="IdEstadoCivil")
	private String idEstadoCivil;

	private char IDEstatus;

	private short IDMunicipio;

	private short IDNacionalidad;

	@Column(name="IdOcupacion")
	private short idOcupacion;

	@Column(name="IdSexo")
	private char idSexo;

	@Column(name="LugarNacimiento")
	private String lugarNacimiento;

	@Size(max=3)
	@Column(name="mun_ced")
	private String munCed;

	@Column(name="Nombres")
	private String nombres;

	@Size(max=7)
	@Column(name="seq_ced")
	private String seqCed;

	@Size(max=1)
	@Column(name="ver_ced")
	private String verCed;

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

	public char getEstatusCedulaAzul() {
		return this.estatusCedulaAzul;
	}

	public void setEstatusCedulaAzul(char estatusCedulaAzul) {
		this.estatusCedulaAzul = estatusCedulaAzul;
	}

	public Timestamp getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public short getIDCategoria() {
		return this.IDCategoria;
	}

	public void setIDCategoria(short IDCategoria) {
		this.IDCategoria = IDCategoria;
	}

	public int getIDCausaCancelacion() {
		return this.idcausa_cancelacion;
	}

	public void setIDCausaCancelacion(int IDCausaCancelacion) {
		this.idcausa_cancelacion = IDCausaCancelacion;
	}

	public int getIDColegio() {
		return this.IDColegio;
	}

	public void setIDColegio(int IDColegio) {
		this.IDColegio = IDColegio;
	}

	public String getIdEstadoCivil() {
		return this.idEstadoCivil;
	}

	public void setIdEstadoCivil(String idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public char getIDEstatus() {
		return this.IDEstatus;
	}

	public void setIDEstatus(char IDEstatus) {
		this.IDEstatus = IDEstatus;
	}

	public short getIDMunicipio() {
		return this.IDMunicipio;
	}

	public void setIDMunicipio(short IDMunicipio) {
		this.IDMunicipio = IDMunicipio;
	}

	public short getIDNacionalidad() {
		return this.IDNacionalidad;
	}

	public void setIDNacionalidad(short IDNacionalidad) {
		this.IDNacionalidad = IDNacionalidad;
	}

	public short getIdOcupacion() {
		return this.idOcupacion;
	}

	public void setIdOcupacion(short idOcupacion) {
		this.idOcupacion = idOcupacion;
	}

	public char getIdSexo() {
		return this.idSexo;
	}

	public void setIdSexo(char idSexo) {
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

}