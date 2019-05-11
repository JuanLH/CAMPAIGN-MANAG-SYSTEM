package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.dao.ILeaderDao;
import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Leader findByCode(String code) {
		return leaderDao.findByCode(code);
	}

	@Override
	public Leader findByEmail(String email) {
		return leaderDao.findByEmail(email);
	}

	@Override
	public Leader findByPhone(String phone) {
		return leaderDao.findByPhone(phone);
	}

	@Override
	public Page<Leader> findAll(Pageable pageable) {
		return leaderDao.findAll(pageable);
	}

	@Override
	public Leader findByCodeAndPassword(String code, String password) {
		return leaderDao.findByCodeAndPassword(code, password);
	}

	@Override
	public Leader findByCedula(String cedula) {
		return leaderDao.findByCedula(cedula);
	}

	@Override
	public Leader findByCedulaAndPassword(String cedula, String password) {
		return leaderDao.findByCedulaAndPassword(cedula, password);
	}

	
}
