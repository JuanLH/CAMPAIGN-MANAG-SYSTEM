package com.juanlhiciano.app.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.juanlhiciano.app.models.entity.Padron2020;

public interface IPadron2k20Dao extends CrudRepository<Padron2020, String>{

	public Padron2020 findByCedula(String cedula);
}
