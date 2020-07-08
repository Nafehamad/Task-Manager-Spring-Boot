package com.userstasks.userstasks.controller;


import com.userstasks.userstasks.entity.Task;
import com.userstasks.userstasks.entity.User;
import com.userstasks.userstasks.service.TaskService;
import com.userstasks.userstasks.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {


    @Autowired
    TaskService taskService;

    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @GetMapping
    public List<Task> findAllTask(){
        return taskService.findAll();
    }

    @GetMapping("/{taskId}")
    public Task findTaskById(@PathVariable("taskId") int taskId){


        Task task=null;
        task=taskService.findById(taskId);
        try {
         if(task==null)
             logger.info("Task id is found " + taskId);
             return task;
        }
        catch (NullPointerException e){
            throw new NullPointerException("User Email not found " +taskId );

        }

    }



   
    @PostMapping
    public void addTask( @RequestBody Task task) {

        User user=new User();
        taskService.save(task);

    }

    @DeleteMapping("/{taskId}")
    @Transactional
    public void deleteTask(@PathVariable("taskId") int taskId){
        taskService.deleteById(taskId);
    }


}
