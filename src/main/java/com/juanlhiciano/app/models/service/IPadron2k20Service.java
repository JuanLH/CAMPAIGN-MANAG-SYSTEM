package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.entity.Padron2k20;

public interface IPadron2k20Service {
	public Padron2k20 findByCedula(String cedula);
}
