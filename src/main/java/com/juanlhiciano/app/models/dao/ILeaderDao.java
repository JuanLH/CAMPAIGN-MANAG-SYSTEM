package com.juanlhiciano.app.models.dao;

import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Voter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ILeaderDao extends PagingAndSortingRepository<Leader, Long> {

	public Leader findByCode(String code);
	public Leader findByCedula(String cedula);
	public Leader findByEmail(String email);
	public Leader findByPhone(String phone);
	public Leader findByCodeAndPassword(String code,String password);
}
