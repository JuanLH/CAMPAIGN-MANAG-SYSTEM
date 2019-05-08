package com.juanlhiciano.app.models.dao;

import com.juanlhiciano.app.models.entity.UserT;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<UserT, String> {
    public UserT findByNameAndPassword(String user,String password);
}
