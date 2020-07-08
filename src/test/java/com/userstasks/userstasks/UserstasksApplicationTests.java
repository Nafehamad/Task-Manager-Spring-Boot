package com.userstasks.userstasks;

import com.userstasks.userstasks.entity.Task;
import com.userstasks.userstasks.entity.User;
import com.userstasks.userstasks.service.TaskService;
import com.userstasks.userstasks.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserstasksApplicationTests {


    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    User user;
    Task task;
    Logger  logger;
    List<User> users;
    List<Task> tasks;


   @BeforeAll
    public  void  init() {
       user=new User();
       task=new Task();
       logger = LoggerFactory.getLogger(UserstasksApplicationTests.class);
       users=new ArrayList<User>();
       tasks=new ArrayList<Task>();
    }

    @Test
    @Transactional
    @Rollback(false)
    void testCreateUser() {
        logger.info("We create new user");
        user.setEmail("saher@gmail.com");
        user.setPassword("saher123");
        user.setAge(24);
        user.setName("saher");
        userService.save(user);
   }

   @Test
   @Transactional
   @Rollback(false)
   void testAssignTask(){

       logger.info("We Assign Task to User");
       task.setDescription("history task");
       task.setCompleted(true);
       user= userService.findById("sam@gmail.com");
       task.setUser(user);
       taskService.save(task);
   }

   @Test
   @Transactional
   void tsetdeleteUser(){


            user=null;
           user= userService.findById("sam@gmail.com");
           if(user==null){
               logger.info("This email does not found");
           }
           else {
               try {
                   userService.deleteById("sam@gmail.com");
                   logger.info("User deleted correctly");
               }
               catch (Exception e){
                   logger.error(" User is a forignkey you can not delet it ");
               }

           }
    }
    @Test
    @Transactional
    void testFindUser(){
       user=null;
       user=userService.findById("sam@gmail.com");
       if (user==null){
           logger.info("This email does not found");
       }
       else {
           System.out.println(user);
       }

    }

    @Test
    @Transactional
    void testFindTask(){
        task=null;
       task= taskService.findById(2);
        if (task==null){
            logger.info("This task does not found");
        }
        else {
            user=task.getUser();
            System.out.println(task);
            System.out.println(user);
        }

    }

    @Test
    @Transactional
    void testFindUsers(){
       users=userService.findAll();
        for(User user:users) {
            System.out.println(user);
        }

    }

    @Test
    @Transactional
    void testFindTasks(){
        tasks = taskService.findAll();
        for(Task task:tasks) {
            System.out.println(task);
            System.out.println(task.getUser());
        }

    }


}
