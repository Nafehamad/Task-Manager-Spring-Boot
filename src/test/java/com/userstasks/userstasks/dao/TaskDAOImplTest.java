package com.userstasks.userstasks.dao;



import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.userstasks.userstasks.dao.UserDAOImpl;
import com.userstasks.userstasks.entity.Task;
import com.userstasks.userstasks.entity.User;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;
import java.util.*;


@RunWith(MockitoJUnitRunner.class)
public class TaskDAOImplTest {

    @Mock
    TaskDAO taskDAO;
    @Mock
    TaskDAOImpl taskDAOImpl;

    @Test
   public void findById() {

        Task task=new Task();
        when(taskDAO.findById(1)).thenReturn( task);
        Assert.assertEquals("",taskDAOImpl.findById(1),null);
        Assert.assertNull(taskDAOImpl.findById(1));

    }

    @Test
  public   void findAll() {

        List<Task> tasks = new ArrayList<Task>();
        when(taskDAO.findAll()).thenReturn(tasks);
        Assert.assertEquals(taskDAOImpl.findAll(),tasks);
    }

    @Test
  public   void save() {

        Task task=new Task();
        taskDAO.save(task);
        when(taskDAO.save(task)).thenReturn(task);
    }

    @Test
   public void deleteById() {

        taskDAO.deleteById(1);
        verify(taskDAO).deleteById(1);

    }
}