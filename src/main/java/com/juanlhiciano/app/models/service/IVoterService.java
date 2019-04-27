package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Sector;
import com.juanlhiciano.app.models.entity.Voter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVoterService {

    public List<Voter> findAll();
    public Page<Voter> findAll(Pageable pageable);
    public void save(Voter voter);
    public Voter findById (String id);
    public void delete(String id);
    public List<Voter> findByLeader(Leader leader);
    public List<Voter> findBySector(Sector sector);
    public Voter findByPhone(String phone);
    public Voter findByEmail(String email);
    public Page<Voter> findByLeader(Leader leader,Pageable page);
    public Page<Voter> findByLeaderAndSector(Leader leader,Sector sector,Pageable page);
    public Page<Voter> findBySector(Sector sector,Pageable page);

}
