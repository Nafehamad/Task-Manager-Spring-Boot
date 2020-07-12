package com.userstasks.userstasks.service;

import com.userstasks.userstasks.dao.TaskDAO;
import com.userstasks.userstasks.entity.Task;
import com.userstasks.userstasks.entity.User;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.junit.Assert;
import org.junit.Test;



@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {


    @Mock
    TaskDAO taskDAO;
    @InjectMocks
    TaskServiceImpl taskServiceImpl;

    @Test
    public void findAll() {

        List<Task> tasks = new ArrayList<Task>();
        tasks=null;
        when(taskDAO.findAll()).thenReturn(tasks);
       // Assert.assertNotNull(taskServiceImpl.findAll());
       Assert.assertEquals ("",taskServiceImpl.findAll(),tasks );

    }

    @Test
    public void findById() {

        Task task=new Task();
        when(taskDAO.findById(1)).thenReturn(task);
        Assert.assertNotNull(taskServiceImpl.findById(1));
        Assert.assertEquals (taskServiceImpl.findById(1).isCompleted(),false);
        Assert.assertEquals (taskServiceImpl.findById(1).getUser(),null);

    }

    @Test
    public void save() {
        Task task=new Task();
        taskDAO.save(task);
        when(taskDAO.save(task)).thenReturn(task);
    }

    @Test
    public void deleteById() {
        User user=new User();

        taskServiceImpl.deleteById(1);
        verify(taskDAO).deleteById(1);

    }



}