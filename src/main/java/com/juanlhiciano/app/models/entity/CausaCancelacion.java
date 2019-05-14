package com.juanlhiciano.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CausaCancelacion database table.
 * 
 */
@Entity
@NamedQuery(name="CausaCancelacion.findAll", query="SELECT c FROM CausaCancelacion c")
public class CausaCancelacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AfectaColegio")
	private String afectaColegio;

	@Column(name="Codigo")
	private short codigo;

	@Column(name="Descripcion")
	private String descripcion;

	@Column(name="Descripcion2016")
	private String descripcion2016;

	@Column(name="DobleEscaneo")
	private String dobleEscaneo;

	@Column(name="RegID")
	private String regID;

	@Column(name="TipoRegistro")
	private String tipoRegistro;

	@Column(name="TituloReporte")
	private String tituloReporte;

	@Column(name="TituloReporteCancelacion")
	private String tituloReporteCancelacion;

	@Column(name="TituloReporteRevalidacion")
	private String tituloReporteRevalidacion;

	//bi-directional many-to-one association to CancelacionTipoCausa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTipoCausaCancelacion", referencedColumnName="ID")
	private CancelacionTipoCausa cancelacionTipoCausa;

	//bi-directional many-to-one association to Padron2020
	@OneToMany(mappedBy="causaCancelacion")
	private List<Padron2020> padron2020s;

	public CausaCancelacion() {
	}

	public String getAfectaColegio() {
		return this.afectaColegio;
	}

	public void setAfectaColegio(String afectaColegio) {
		this.afectaColegio = afectaColegio;
	}

	public short getCodigo() {
		return this.codigo;
	}

	public void setCodigo(short codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion2016() {
		return this.descripcion2016;
	}

	public void setDescripcion2016(String descripcion2016) {
		this.descripcion2016 = descripcion2016;
	}

	public String getDobleEscaneo() {
		return this.dobleEscaneo;
	}

	public void setDobleEscaneo(String dobleEscaneo) {
		this.dobleEscaneo = dobleEscaneo;
	}

	public String getRegID() {
		return this.regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

	public String getTipoRegistro() {
		return this.tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getTituloReporte() {
		return this.tituloReporte;
	}

	public void setTituloReporte(String tituloReporte) {
		this.tituloReporte = tituloReporte;
	}

	public String getTituloReporteCancelacion() {
		return this.tituloReporteCancelacion;
	}

	public void setTituloReporteCancelacion(String tituloReporteCancelacion) {
		this.tituloReporteCancelacion = tituloReporteCancelacion;
	}

	public String getTituloReporteRevalidacion() {
		return this.tituloReporteRevalidacion;
	}

	public void setTituloReporteRevalidacion(String tituloReporteRevalidacion) {
		this.tituloReporteRevalidacion = tituloReporteRevalidacion;
	}

	public CancelacionTipoCausa getCancelacionTipoCausa() {
		return this.cancelacionTipoCausa;
	}

	public void setCancelacionTipoCausa(CancelacionTipoCausa cancelacionTipoCausa) {
		this.cancelacionTipoCausa = cancelacionTipoCausa;
	}

	public List<Padron2020> getPadron2020s() {
		return this.padron2020s;
	}

	public void setPadron2020s(List<Padron2020> padron2020s) {
		this.padron2020s = padron2020s;
	}

	public Padron2020 addPadron2020(Padron2020 padron2020) {
		getPadron2020s().add(padron2020);
		padron2020.setCausaCancelacion(this);

		return padron2020;
	}

	public Padron2020 removePadron2020(Padron2020 padron2020) {
		getPadron2020s().remove(padron2020);
		padron2020.setCausaCancelacion(null);

		return padron2020;
	}

}