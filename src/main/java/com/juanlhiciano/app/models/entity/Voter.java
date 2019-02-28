package com.juanlhiciano.app.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

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
    String id;

    String name;

    String phone;

    @Email
    @NotEmpty
    String email;

    @Column(name = "electoral_school")
    String electoralSchool;

    @Temporal(TemporalType.DATE)
    Date registrationDate;

    @ManyToOne
    @JsonIgnoreProperties(value={"voters", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    Leader leader;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Sector sector;

    @PrePersist
    public void prePersist() {
        registrationDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getElectoralSchool() {
        return electoralSchool;
    }

    public void setElectoralSchool(String electoralSchool) {
        this.electoralSchool = electoralSchool;
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
