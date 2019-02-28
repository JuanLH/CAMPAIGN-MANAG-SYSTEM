package com.juanlhiciano.app.models.dao;

import com.juanlhiciano.app.models.entity.Sector;
import org.springframework.data.repository.CrudRepository;

public interface ISectorDao extends CrudRepository<Sector, Integer> {
}
