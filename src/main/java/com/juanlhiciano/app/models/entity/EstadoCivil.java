package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EstadoCivil database table.
 * 
 */
@Entity
@NamedQuery(name="EstadoCivil.findAll", query="SELECT e FROM EstadoCivil e")
public class EstadoCivil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="Id")
	private String id;

	@Column(name="RegID")
	private String regID;

	public EstadoCivil() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegID() {
		return this.regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

}