package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.dao.ISectorDao;
import com.juanlhiciano.app.models.entity.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService implements ISectorService {

    @Autowired
    ISectorDao sectorDao;

    @Override
    public List<Sector> findAll() {
        return (List<Sector>) sectorDao.findAll();
    }

    @Override
    public void save(Sector sector) {
        sectorDao.save(sector);
    }

    @Override
    public Sector findById(int id) {
        return sectorDao.findById(id).get();
    }

    @Override
    public void delete(int id) {
        sectorDao.delete(findById(id));
    }

}
