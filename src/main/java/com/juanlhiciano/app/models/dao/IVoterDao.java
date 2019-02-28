package com.juanlhiciano.app.models.dao;

import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Voter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IVoterDao extends CrudRepository<Voter,String> {
    public List<Voter> findByLeader(Optional<Leader> leader);
}
