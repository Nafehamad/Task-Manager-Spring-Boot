package com.userstasks.userstasks.service;

import com.userstasks.userstasks.entity.User;
import com.userstasks.userstasks.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(String userEmail) {
        return userDAO.findById(userEmail);
    }

    @Override
    public void save(User user) {
        userDAO.save(user);

    }

    @Override
    public void deleteById(String userEmail) {
        userDAO.deleteById(userEmail);

    }

}
