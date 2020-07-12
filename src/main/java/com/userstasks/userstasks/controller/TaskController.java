package com.userstasks.userstasks.controller;


import com.userstasks.userstasks.entity.Task;
import com.userstasks.userstasks.entity.User;
import com.userstasks.userstasks.service.TaskService;
import com.userstasks.userstasks.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {


    @Autowired
    private TaskService taskService;

   private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @GetMapping
    public ResponseEntity findAllTask(){

        try {
            return ResponseEntity.ok(taskService.findAll());
        } catch (Exception ex){
            logger.error("An error occurred during getting list of tasks, {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during getting list of tasks");
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity findTaskById(@PathVariable("taskId") int taskId){

        try {
            return ResponseEntity.ok(taskService.findById(taskId));
        } catch (Exception ex){
            logger.error("An error occurred during getting a task, {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during getting a task");
        }

    }



   
    @PostMapping
    public ResponseEntity addTask( @RequestBody Task task) {

        try {
            logger.info("task inserted correctly");
            return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(task));

        } catch (Exception ex){
            logger.error("An error occurred during saving a new user, {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during saving a new task");
        }


    }

    @DeleteMapping("/{taskId}")
    @Transactional
    public ResponseEntity deleteTask(@PathVariable("taskId") int taskId){

        try {
            taskService.deleteById(taskId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("The task deleted successfully");
        } catch (Exception ex){
            logger.error("An error occurred during deleting a task, {}", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during deleting a task");
        }

    }


}
