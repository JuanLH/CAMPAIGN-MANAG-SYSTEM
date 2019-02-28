package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.entity.User;

import java.util.List;

public interface IUserService {

    public List<User> findAll();
    public void save(User user);
    public User findById (String id);
    public void delete(String id);
}
