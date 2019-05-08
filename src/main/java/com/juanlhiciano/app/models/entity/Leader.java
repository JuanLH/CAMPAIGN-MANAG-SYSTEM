package com.juanlhiciano.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "leaders")
public class Leader implements Serializable  {

   private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty
    @Column(unique = true)
    @Pattern(regexp="(^$|[0-9]{10})")
    String phone;
    
    @Column(unique=true)
    @NotEmpty
    @Pattern(regexp="(^$|[0-9]{11})")
    String cedula;
    
    
    @Email
    @Column(unique = true)
    @NotEmpty
    @Pattern(regexp="\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}\\b")
    String email;

    @NotEmpty
    String name;

    @NotEmpty
    @Column(unique = true)
    @Pattern(regexp="([A-Z]{2}[0-9]{3})")
    String code;

    @NotEmpty
    String password;
    
	@OneToMany(mappedBy = "leader",  fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value={"leader", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    private  List<Voter> voters;


    public Leader() {
        voters = new ArrayList<>();
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCelular() {
        return phone;
    }

    public void setCelular(String celular) {
        this.phone = celular;
    }    
    
    public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> votantes) {
        this.voters = votantes;
    }

    public void addVoter(Voter votante){
        voters.add(votante);
    }
    
    public String toString() {
		return name+" - "+code;
    	
    }
    
    public String getPassword() {
		return password;
	}
    
	public void setPassword(String password) {
		this.password = password;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	
}
