package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.dao.IUserDao;
import com.juanlhiciano.app.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  implements IUserService{

    @Autowired
    IUserDao userDao;

    @Override
    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    @Override
    public void delete(String id) {
        userDao.delete(findById(id));
    }
}
