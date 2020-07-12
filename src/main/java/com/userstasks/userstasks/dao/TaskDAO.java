package com.userstasks.userstasks.dao;

import com.userstasks.userstasks.entity.Task;


import java.util.List;

public interface TaskDAO {

    public Task findById(int taskId);
    public List<Task> findAll();
    public Task save(Task task);
    public void deleteById(int taskId);
}
