package com.juanlhiciano.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanlhiciano.app.models.dao.IPadron2k20Dao;
import com.juanlhiciano.app.models.entity.Padron2020;

@Service
public class Padron2020Service implements IPadron2020Service {

	@Autowired
	IPadron2k20Dao padron;

	@Override
	public Padron2020 findByCedula(String cedula) {
		return padron.findByCedula(cedula);
	}
	
	
}
