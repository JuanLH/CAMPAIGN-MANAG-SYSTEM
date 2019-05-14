package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Zona database table.
 * 
 */
@Entity
@NamedQuery(name="Zona.findAll", query="SELECT z FROM Zona z")
public class Zona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="ID")
	private String id;

	public Zona() {
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

}