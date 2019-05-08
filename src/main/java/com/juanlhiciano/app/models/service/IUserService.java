package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.entity.UserT;

import java.util.List;

public interface IUserService {

    public List<UserT> findAll();
    public void save(UserT userT);
    public UserT findById (String id);
    public void delete(String id);
    public UserT findByNameAndPassword(String user,String password);
}
