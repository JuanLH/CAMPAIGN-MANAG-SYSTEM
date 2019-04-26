package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Voter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ILeaderService {

    public List<Leader> findAll();
    public Page<Leader> findAll(Pageable pageable);
    public void save(Leader cliente);
    public Leader findById (Long id);
    public void delete(Long id);
    public Leader findByCode(String code);
    public Leader findByEmail(String email);
    public Leader findByPhone(String phone);
    public Leader findByCodeAndPassword(String code,String password);
}
