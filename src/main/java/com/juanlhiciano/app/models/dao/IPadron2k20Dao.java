package com.juanlhiciano.app.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.juanlhiciano.app.models.entity.Padron2k20;

public interface IPadron2k20Dao extends CrudRepository<Padron2k20, String>{

	public Padron2k20 findByCedula(String cedula);
}
