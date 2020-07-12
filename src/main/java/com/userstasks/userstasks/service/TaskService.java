package com.userstasks.userstasks.service;

import com.userstasks.userstasks.entity.Task;


import java.util.List;

public interface TaskService {


    public List<Task> findAll();
    public Task findById(int taskId);
    public Task save(Task task);
    public void deleteById(int taskId);

}
