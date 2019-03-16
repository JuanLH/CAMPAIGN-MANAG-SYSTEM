package com.juanlhiciano.app.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sectors")
public class Sector implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotEmpty
    String name;

    @OneToMany(mappedBy = "sector", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Voter> voters;

    public Sector() {
        voters = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Voter> getVotantes() {
        return voters;
    }

    public void setVotantes(List<Voter> votantes) {
        this.voters = votantes;
    }

    public void addVotante(Voter votante){
        voters.add(votante);
    }
}
