package com.juanlhiciano.app.models.service;

import java.util.List;

import com.juanlhiciano.app.models.entity.UserType;

public interface IUserTypeService {
	public List<UserType> findAll();
	public void save(UserType userType);
	public UserType findById(int id);
	public void delete(int id);
	
}
