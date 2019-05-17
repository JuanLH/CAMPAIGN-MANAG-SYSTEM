package com.juanlhiciano.app.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "voters")
public class Voter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Pattern(regexp="(^$|[0-9]{11})")
    @NotEmpty
    String cedula;//***

    
    @Pattern(regexp="^[ña-zÑA-Z ]+(([',. -][ña-zÑA-Z ])?[ña-zÑA-Z]*)*$")
    String names;
    
    
    @Pattern(regexp="^[ña-zÑA-Z]+(([',. -][ña-zÑA-Z ])?[ña-zÑA-Z]*)*$")
    String LastName1;

    
    @Pattern(regexp="^[ña-zÑA-Z]+(([',. -][ña-zÑA-Z ])?[ña-zÑA-Z]*)*$")
	String LastName2;
    
    Date dob;
    
    String placeOfBirth;
    
    short categoria;
    
    char sexo;
    
    String estadoCivil;
    
    short ocupacion;
    
    short nacionalidad;
    
    short municipio;
    
    int colegioElectoral;
    
    String munCed;
    String seqCed;
    String verCed;
    
    @Column(unique = true)
    @Pattern(regexp="(^$|[0-9]{10})")
    String phone;//***
    
    @Column(unique = true)
    @Pattern(regexp="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,63}$")
    String email;//***

    @Column(name="registration_date")
    @Temporal(TemporalType.DATE)
    Date registration;//***

    @ManyToOne
    @JsonIgnoreProperties(value={"voters", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    Leader leader;//***

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Sector sector;//***
    
    @Pattern(regexp="^[ña-zÑA-Z ]+(([',. -][ña-zÑA-Z ])?[ña-zÑA-Z]*)*$")
    String pensar;//***
    
    @Pattern(regexp="^[ña-zÑA-Z ]+(([',. -][ña-zÑA-Z ])?[ña-zÑA-Z]*)*$")
    String necesidad;//***
    
    @Column(name="check_mark")
    Boolean check;

    @PreUpdate
    @PrePersist
    public void prePersist() {
        registration = new Date();
        check = false;
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

	public void setDob(Date date) {
		this.dob = date;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	

	public short getCategoria() {
		return categoria;
	}

	public char getIdSexo() {
		return sexo;
	}

	public void setIdSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public short getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(short ocupacion) {
		this.ocupacion = ocupacion;
	}

	public short getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(short nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public short getMunicipio() {
		return municipio;
	}

	public void setMunicipio(short municipio) {
		this.municipio = municipio;
	}

	public int getColegioElectoral() {
		return colegioElectoral;
	}

	public void setColegioElectoral(int colegioElectoral) {
		this.colegioElectoral = colegioElectoral;
	}

	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	public void setCategoria(short categoria) {
		this.categoria = categoria;
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
		return registration;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registration = registrationDate;
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

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}
	
	


	
	

    
    
    
}
