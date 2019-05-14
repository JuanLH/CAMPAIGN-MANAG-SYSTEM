package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Recinto database table.
 * 
 */
@Entity
@NamedQuery(name="Recinto.findAll", query="SELECT r FROM Recinto r")
public class Recinto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CapacidadRecinto")
	private int capacidadRecinto;

	@Column(name="Codigo")
	private short codigo;

	@Column(name="CodigoRecinto")
	private String codigoRecinto;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="DescripcionLarga")
	private String descripcionLarga;

	@Column(name="Direccion")
	private String direccion;

	@Column(name="DireccionLarga")
	private String direccionLarga;

	@Column(name="Estatus")
	private String estatus;

	private int IDBarrio;

	@Column(name="Oficio")
	private int oficio;

	@Column(name="RegID")
	private String regID;

	@Column(name="Tipo")
	private String tipo;

	//bi-directional many-to-one association to Colegio
	@OneToMany(mappedBy="recinto")
	private List<Colegio> colegios;

	//bi-directional many-to-one association to SectorParaje
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSectorParaje", referencedColumnName="ID")
	private SectorParaje sectorParaje;

	//bi-directional many-to-one association to Circunscripcion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCircunscripcion", referencedColumnName="ID")
	private Circunscripcion circunscripcion;

	public Recinto() {
	}

	public int getCapacidadRecinto() {
		return this.capacidadRecinto;
	}

	public void setCapacidadRecinto(int capacidadRecinto) {
		this.capacidadRecinto = capacidadRecinto;
	}

	public short getCodigo() {
		return this.codigo;
	}

	public void setCodigo(short codigo) {
		this.codigo = codigo;
	}

	public String getCodigoRecinto() {
		return this.codigoRecinto;
	}

	public void setCodigoRecinto(String codigoRecinto) {
		this.codigoRecinto = codigoRecinto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionLarga() {
		return this.descripcionLarga;
	}

	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDireccionLarga() {
		return this.direccionLarga;
	}

	public void setDireccionLarga(String direccionLarga) {
		this.direccionLarga = direccionLarga;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public int getIDBarrio() {
		return this.IDBarrio;
	}

	public void setIDBarrio(int IDBarrio) {
		this.IDBarrio = IDBarrio;
	}

	public int getOficio() {
		return this.oficio;
	}

	public void setOficio(int oficio) {
		this.oficio = oficio;
	}

	public String getRegID() {
		return this.regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Colegio> getColegios() {
		return this.colegios;
	}

	public void setColegios(List<Colegio> colegios) {
		this.colegios = colegios;
	}

	public Colegio addColegio(Colegio colegio) {
		getColegios().add(colegio);
		colegio.setRecinto(this);

		return colegio;
	}

	public Colegio removeColegio(Colegio colegio) {
		getColegios().remove(colegio);
		colegio.setRecinto(null);

		return colegio;
	}

	public SectorParaje getSectorParaje() {
		return this.sectorParaje;
	}

	public void setSectorParaje(SectorParaje sectorParaje) {
		this.sectorParaje = sectorParaje;
	}

	public Circunscripcion getCircunscripcion() {
		return this.circunscripcion;
	}

	public void setCircunscripcion(Circunscripcion circunscripcion) {
		this.circunscripcion = circunscripcion;
	}

}