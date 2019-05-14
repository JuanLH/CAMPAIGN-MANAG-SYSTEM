package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Sexo database table.
 * 
 */
@Entity
@NamedQuery(name="Sexo.findAll", query="SELECT s FROM Sexo s")
public class Sexo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="FechaCreacion")
	private Timestamp fechaCreacion;

	@Column(name="FechaModificacion")
	private Timestamp fechaModificacion;

	@Column(name="IdSexo")
	private String idSexo;

	@Column(name="IdUsuarioCreacion")
	private int idUsuarioCreacion;

	@Column(name="IdUsuarioModificacion")
	private int idUsuarioModificacion;

	@Column(name="RegID")
	private String regID;

	public Sexo() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getIdSexo() {
		return this.idSexo;
	}

	public void setIdSexo(String idSexo) {
		this.idSexo = idSexo;
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

	public String getRegID() {
		return this.regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

}