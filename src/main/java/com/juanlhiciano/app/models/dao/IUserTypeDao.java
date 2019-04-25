package com.juanlhiciano.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.juanlhiciano.app.models.entity.UserType;

public interface IUserTypeDao extends CrudRepository<UserType,Integer> {

}
