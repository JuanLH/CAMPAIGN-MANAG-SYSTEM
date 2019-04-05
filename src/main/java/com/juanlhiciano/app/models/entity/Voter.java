package com.juanlhiciano.app.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "voters")
public class Voter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    String cedula;//***

    String names;
    
    String LastName1;
    
    public String getPensar() {
		return pensar;
	}

	public void setPensar(String pensar) {
		this.pensar = pensar;
	}

	public String getNecesidad() {
		return necesidad;
	}

	public void setNecesidad(String necesidad) {
		this.necesidad = necesidad;
	}

	String LastName2;
    
    Date dob;
    
    String placeOfBirth;
    
    int idCategoria;
    
    String idSexo;
    
    String estadoCivil;
    
    int idOcupacion;
    
    int idNacionalidad;
    
    String idMunicipio;
    
    String colegioElectoral;
    
    String munCed;
    String seqCed;
    String verCed;

    
    
    String phone;//***
    
    @Email
    @NotEmpty
    String email;//***

    @Column(name="registration_date")
    @Temporal(TemporalType.DATE)
    Date registrationDate;//***

    @ManyToOne
    @JsonIgnoreProperties(value={"voters", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    Leader leader;//***

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Sector sector;//***
    
    String pensar;//***
    
    String necesidad;//***

    @PrePersist
    public void prePersist() {
        registrationDate = new Date();
    }
    
    //---------------------------------------------------

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLastName1() {
		return LastName1;
	}

	public void setLastName1(String lastName1) {
		LastName1 = lastName1;
	}

	public String getLastName2() {
		return LastName2;
	}

	public void setLastName2(String lastName2) {
		LastName2 = lastName2;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(String idSexo) {
		this.idSexo = idSexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public int getIdOcupacion() {
		return idOcupacion;
	}

	public void setIdOcupacion(int idOcupacion) {
		this.idOcupacion = idOcupacion;
	}

	public int getIdNacionalidad() {
		return idNacionalidad;
	}

	public void setIdNacionalidad(int idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getColegioElectoral() {
		return colegioElectoral;
	}

	public void setColegioElectoral(String colegioElectoral) {
		this.colegioElectoral = colegioElectoral;
	}

	public String getMunCed() {
		return munCed;
	}

	public void setMunCed(String munCed) {
		this.munCed = munCed;
	}

	public String getSeqCed() {
		return seqCed;
	}

	public void setSeqCed(String seqCed) {
		this.seqCed = seqCed;
	}

	public String getVerCed() {
		return verCed;
	}

	public void setVerCed(String verCed) {
		this.verCed = verCed;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Leader getLeader() {
		return leader;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

    
    
    
}
