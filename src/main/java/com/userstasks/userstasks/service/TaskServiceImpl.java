package com.userstasks.userstasks.service;

import com.userstasks.userstasks.dao.TaskDAO;
import com.userstasks.userstasks.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskServiceImpl implements TaskService{


    @Autowired
    private TaskDAO taskDAO;

    @Override
    public List<Task> findAll() {
        return taskDAO.findAll();
    }

    @Override
    public Task findById(int taskId) {


       return taskDAO.findById(taskId);
    }

    @Override
    public void save(Task task) {
        taskDAO.save((task));

    }

    @Override
    public void deleteById(int taskId) {

        taskDAO.deleteById(taskId);
    }
}
