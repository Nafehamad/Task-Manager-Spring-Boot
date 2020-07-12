package com.userstasks.userstasks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.userstasks.userstasks.entity.User;
import com.userstasks.userstasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

   private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity findAllUser() {

       try {
            return ResponseEntity.ok(userService.findAll());
        } catch (Exception ex){
            logger.error("An error occurred during getting list of tasks, {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during getting list of tasks");
        }
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity findUserById(@PathVariable("userEmail") String userEmail) {

        try {
            return ResponseEntity.ok(userService.findById(userEmail));

        } catch (Exception ex){
            logger.error("An error occurred during getting a task, {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during getting a task");
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity addUser(@RequestBody User user) {

        try {
            logger.info("user inserted correctly");
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));

        } catch (Exception ex){
            logger.error("An error occurred during saving a new user, {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during saving a new user");
        }

    }

    @DeleteMapping("/{userEmail}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable("userEmail") String userEmail) {

        try {
            userService.deleteById(userEmail);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("The user deleted successfully");
        } catch (Exception ex){
            logger.error("An error occurred during deleting a task, {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during deleting a user");
        }


    }
}