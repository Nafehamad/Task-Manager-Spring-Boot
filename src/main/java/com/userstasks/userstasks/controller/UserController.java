package com.userstasks.userstasks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.userstasks.userstasks.entity.User;
import com.userstasks.userstasks.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> findAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{userEmail}")

    public User findUserById(@PathVariable("userEmail") String userEmail) {

        User user = userService.findById(userEmail);
        if (user == null) {
            logger.warn("Employee id not found " + userEmail);
        }
        return user;

    }

    @PostMapping
    @Transactional
    public void addUser(@RequestBody User user) {

        userService.save(user);


    }

    @DeleteMapping("/{userEmail}")
    @Transactional
    public void deleteUser(@PathVariable("userEmail") String userEmail) {

        try {
            userService.deleteById(userEmail);
        }
        catch (Exception e) {
            logger.error(" User"+ userEmail +"is a forignkey you can not delet it ");
        }

    }
}