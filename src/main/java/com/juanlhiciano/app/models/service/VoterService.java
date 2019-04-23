package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.dao.ILeaderDao;
import com.juanlhiciano.app.models.dao.IVoterDao;
import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Sector;
import com.juanlhiciano.app.models.entity.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterService implements IVoterService{

    @Autowired
    private IVoterDao voterDao;

    @Autowired
    private ILeaderDao leaderDao;

    @Override
    public List<Voter> findAll() {
        return (List<Voter>) voterDao.findAll();
    }

    @Override
    public void save(Voter voter) {
        voterDao.save(voter);
    }

    @Override
    public Voter findById(String id) {
        return voterDao.findById(id).get();
    }

    @Override
    public void delete(String id) {
        voterDao.delete(findById(id));
    }

    @Override
    public List<Voter> findByLeader(Leader leader) {
        return voterDao.findByLeader(leader);
    }

	@Override
	public List<Voter> findBySector(Sector sector) {
		return voterDao.findBySector(sector);
	}

	@Override
	public Voter findByPhone(String phone) {
		return voterDao.findByPhone(phone);
	}

	@Override
	public Voter findByEmail(String email) {
		return voterDao.findByEmail(email);
	}

	@Override
	public Page<Voter> findAll(Pageable pageable) {
		return voterDao.findAll(pageable);
	}

	@Override
	public Page<Voter> findByLeader(Leader leader, Pageable page) {
		return voterDao.findByLeader(leader, page);
	}

	@Override
	public Page<Voter> findBySector(Sector sector, Pageable page) {
		return voterDao.findBySector(sector, page);
	}
}
