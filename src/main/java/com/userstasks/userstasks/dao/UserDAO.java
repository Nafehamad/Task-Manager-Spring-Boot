package com.userstasks.userstasks.dao;

import com.userstasks.userstasks.entity.User;

import java.util.List;

public interface UserDAO {

    public User findById(String userEmail);
    public List<User> findAll();
    public void save(User user);
    public void deleteById(String userEmail);

}
