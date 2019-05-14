package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MacroRegion database table.
 * 
 */
@Entity
@NamedQuery(name="MacroRegion.findAll", query="SELECT m FROM MacroRegion m")
public class MacroRegion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="ID")
	private int id;

	public MacroRegion() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}