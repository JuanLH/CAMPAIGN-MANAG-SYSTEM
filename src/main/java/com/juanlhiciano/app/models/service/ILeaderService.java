package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Voter;

import java.util.List;

public interface ILeaderService {

    public List<Leader> findAll();
    public void save(Leader cliente);
    public Leader findById (Long id);
    public void delete(Long id);
}
