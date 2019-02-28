package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.dao.ILeaderDao;
import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeaderService implements ILeaderService {

    @Autowired
    ILeaderDao leaderDao;

    @Override
    @Transactional(readOnly = true)
    public List<Leader> findAll() {
        return (List<Leader>) leaderDao.findAll();
    }

    @Override
    @Transactional
    public void save(Leader leader) {
        leaderDao.save(leader);
    }

    @Override
    @Transactional(readOnly = true)
    public Leader findById(Long id) {
        return leaderDao.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        leaderDao.delete(findById(id));
    }
}
