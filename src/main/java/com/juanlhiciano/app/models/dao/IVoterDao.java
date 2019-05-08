package com.juanlhiciano.app.models.dao;

import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Sector;
import com.juanlhiciano.app.models.entity.Voter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface IVoterDao extends PagingAndSortingRepository<Voter,String> {
	
    public List<Voter> findByLeader(Leader leader);
    public List<Voter> findBySector(Sector sector);
    public Voter findByPhone(String phone);
    public Voter findByEmail(String email);
    public Page<Voter> findAll(Pageable page);
    public Page<Voter> findByLeader(Leader leader,Pageable page);
    public Page<Voter> findByLeaderAndSector(Leader leader,Sector sector,Pageable page);
    public Page<Voter> findBySector(Sector sector,Pageable page);
    public Page<Voter> findByCheck(Boolean check,Pageable page);
}
