package com.juanlhiciano.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    String phone;

    @NotEmpty
    String name;

    @NotEmpty
    @Column(unique = true)
    String code;

    @OneToMany(mappedBy = "leader",  fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value={"leader", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    private  List<Voter> voters;


    public Leader() {
        voters = new ArrayList<>();
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

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
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
}
