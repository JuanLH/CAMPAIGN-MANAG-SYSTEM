package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Voter;

import java.util.List;

public interface IVoterService {

    public List<Voter> findAll();
    public void save(Voter voter);
    public Voter findById (String id);
    public void delete(String id);
    public List<Voter> findByLeader(Long id);

}
