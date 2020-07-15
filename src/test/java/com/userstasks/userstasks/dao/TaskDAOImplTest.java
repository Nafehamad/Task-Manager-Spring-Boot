package com.userstasks.userstasks.dao;



import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;


@RunWith(MockitoJUnitRunner.class)
public class TaskDAOImplTest {
    @Mock
    EntityManager entityManager;
    @Mock
    Query query;
    @Mock
    CriteriaQuery criteriaQuery;
    @Mock
    TypedQuery<Task> query1;
    @Mock
    Session session;
    @Mock
    TaskDAO taskDAO;
    @InjectMocks
    TaskDAOImpl taskDAOImpl;
    private List<Task> taskList=new ArrayList<Task>();


    @Test
   public void findById() {

        Task task=new Task(1,"one",false);
        when(entityManager.createQuery(any(String.class), eq(Task.class))).thenReturn(query1);
        when(query1.setParameter(any(String.class), any(String.class))).thenReturn(query1);
        when(query1.getSingleResult()).thenReturn(task);

    }

    @Test
  public   void findAll() {

        Task task=new Task(1,"one",false);
        when(entityManager.createQuery(any(String.class), eq(Task.class))).thenReturn(query1);
        when(query1.setParameter(any(String.class), any(String.class))).thenReturn(query1);
        when(query1.getResultList()).thenReturn(taskList);
    }

    @Test
  public  void save() {

        Task task=new Task();
        taskDAO.save(task);
        when(taskDAO.save(task)).thenReturn(task);
    }

    @Test
   public void deleteById() {

        int id=1;
        Task task=new Task();
        when(entityManager.createQuery("delete from Task where id=:id")).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

    }
}