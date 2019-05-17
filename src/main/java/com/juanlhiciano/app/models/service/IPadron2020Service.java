package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.entity.Padron2020;

public interface IPadron2020Service {
	public Padron2020 findByCedula(String cedula);
}
