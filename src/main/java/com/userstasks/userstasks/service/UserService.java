package com.userstasks.userstasks.service;

import com.userstasks.userstasks.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();
    public User findById(String userEmail);
    public User save(User user);
    public void deleteById(String userEmail);



}
