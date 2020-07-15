package com.userstasks.userstasks.dao;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import com.userstasks.userstasks.entity.Task;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.hibernate.query.Query;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;


@RunWith(MockitoJUnitRunner.class)
public class TaskDAOImplTest {
    @Mock
    EntityManager entityManager;
    @Mock
    Query <Task> query;
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
        when(entityManager.unwrap(Session.class)).thenReturn(session);
        when(session.get(Task.class,1)).thenReturn(task);
        taskDAOImpl.findById(1);
        assertEquals(taskDAOImpl.findById(1).getDescription(),"one");
        assertEquals(taskDAOImpl.findById(1).isCompleted(),false);


    }

    @Test
  public   void findAll() {

        Task task=new Task(1,"one",false);
        Task task1=new Task(2,"one",false);
        Task task2=new Task(3,"one",false);
        taskList.add(task);
        taskList.add(task);
        when(entityManager.unwrap(Session.class)).thenReturn(session);
        when(session.createQuery(any(String.class), eq(Task.class))).thenReturn(query);
        when(query.setParameter(any(String.class), any(String.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(taskList);
        Assert.assertEquals(taskDAOImpl.findAll().size(),3);
    }

    @Test
  public  void save() {

        Task task=new Task(1,"one",false);
        when(entityManager.unwrap(Session.class)).thenReturn(session);
        taskDAOImpl.save(task);
    }

    @Test
   public void deleteById() {

        int id=1;
        Task task=new Task(1,"one",false);
        when(entityManager.unwrap(Session.class)).thenReturn(session);
        when(session.createQuery("delete from Task where id=:id")).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);
        taskDAOImpl.deleteById(id);

    }
}