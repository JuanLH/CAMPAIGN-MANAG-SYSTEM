package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.entity.Sector;

import java.util.List;

public interface ISectorService {

    public List<Sector> findAll();
    public void save(Sector cliente);
    public Sector findById (int id);
    public void delete(int id);

}
