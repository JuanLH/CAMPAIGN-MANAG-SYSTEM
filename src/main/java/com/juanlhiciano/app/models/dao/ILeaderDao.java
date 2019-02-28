package com.juanlhiciano.app.models.dao;

import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Voter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ILeaderDao extends CrudRepository<Leader, Long> {


}
