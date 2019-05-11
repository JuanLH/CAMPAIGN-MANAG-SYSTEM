package com.juanlhiciano.app.models.dao;

import com.juanlhiciano.app.models.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, String> {
    public User findByNameAndPassword(String user,String password);
    public User findByCedulaAndPassword(String cedula,String password);
    public User findByCedula(String cedula);
}
