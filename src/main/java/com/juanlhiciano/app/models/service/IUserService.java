package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.entity.User;

import java.util.List;

public interface IUserService {

    public List<User> findAll();
    public void save(User user);
    public User findById (String id);
    public void delete(String id);
    public User findByNameAndPassword(String user,String password);
    public User findByCedulaAndPassword(String cedula,String password);
    public User findByCedula(String cedula);
}
