package com.juanlhiciano.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanlhiciano.app.models.dao.IUserTypeDao;
import com.juanlhiciano.app.models.entity.UserType;

@Service
public class UserTypeService implements IUserTypeService {

	@Autowired
	IUserTypeDao userTypeDao;
	
	@Override
	public List<UserType> findAll() {
		// TODO Auto-generated method stub
		return (List<UserType>) userTypeDao.findAll();
	}

	@Override
	public void save(UserType userType) {
		userTypeDao.save(userType);
		
	}

	@Override
	public UserType findById(int id) {
		return userTypeDao.findById(id).get();
	}

	@Override
	public void delete(int id) {
		userTypeDao.delete(findById(id));
		
	}

}
