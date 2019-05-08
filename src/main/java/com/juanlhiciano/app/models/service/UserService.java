package com.juanlhiciano.app.models.service;

import com.juanlhiciano.app.models.dao.IUserDao;
import com.juanlhiciano.app.models.entity.UserT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  implements IUserService{

    @Autowired
    IUserDao userDao;

    @Override
    public List<UserT> findAll() {
        return (List<UserT>) userDao.findAll();
    }

    @Override
    public void save(UserT userT) {
        userDao.save(userT);
    }

    @Override
    public UserT findById(String id) {
        return userDao.findById(id).get();
    }

    @Override
    public void delete(String id) {
        userDao.delete(findById(id));
    }

    @Override
    public UserT findByNameAndPassword(String user, String password) {
        return userDao.findByNameAndPassword(user,password);
    }

}
