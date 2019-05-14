package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CancelacionTipoCausa database table.
 * 
 */
@Entity
@NamedQuery(name="CancelacionTipoCausa.findAll", query="SELECT c FROM CancelacionTipoCausa c")
public class CancelacionTipoCausa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Codigo")
	private String codigo;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="RegID")
	private String regID;

	//bi-directional many-to-one association to CausaCancelacion
	@OneToMany(mappedBy="cancelacionTipoCausa")
	private List<CausaCancelacion> causaCancelacions;

	public CancelacionTipoCausa() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRegID() {
		return this.regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

	public List<CausaCancelacion> getCausaCancelacions() {
		return this.causaCancelacions;
	}

	public void setCausaCancelacions(List<CausaCancelacion> causaCancelacions) {
		this.causaCancelacions = causaCancelacions;
	}

	public CausaCancelacion addCausaCancelacion(CausaCancelacion causaCancelacion) {
		getCausaCancelacions().add(causaCancelacion);
		causaCancelacion.setCancelacionTipoCausa(this);

		return causaCancelacion;
	}

	public CausaCancelacion removeCausaCancelacion(CausaCancelacion causaCancelacion) {
		getCausaCancelacions().remove(causaCancelacion);
		causaCancelacion.setCancelacionTipoCausa(null);

		return causaCancelacion;
	}

}