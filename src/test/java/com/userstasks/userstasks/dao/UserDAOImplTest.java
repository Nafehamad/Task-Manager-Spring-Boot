package com.userstasks.userstasks.dao;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

import com.userstasks.userstasks.dao.UserDAOImpl;
import com.userstasks.userstasks.entity.Task;
import com.userstasks.userstasks.entity.User;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.*;


@RunWith(MockitoJUnitRunner.class)
public class UserDAOImplTest {



    @Mock
    EntityManager entityManager;
    @Mock
    Query query;
    @Mock
    CriteriaQuery criteriaQuery;
    @Mock
    TypedQuery<User> query1;
    @Mock
    Session session;
    @Mock
    UserDAO userDAO;

    @InjectMocks
    UserDAOImpl userDAOImpl;

    private List<User> userList=new ArrayList<User>();



    @Test
    public void findById() {

          User user=new User("a","a@a","a",10);
          when(entityManager.createQuery(any(String.class), eq(User.class))).thenReturn(query1);
          when(query1.setParameter(any(String.class), any(String.class))).thenReturn(query1);
          when(query1.getSingleResult()).thenReturn(user);


    }

   @Test
   public void findAll() {

       User user=new User("a","a@a","a",10);
       when(entityManager.createQuery(any(String.class), eq(User.class))).thenReturn(query1);
       when(query1.setParameter(any(String.class), any(String.class))).thenReturn(query1);
       when(query1.getResultList()).thenReturn(userList);

    }

   @Test
  public void save() {

       User user1=new User();
       userDAO.save(user1);
       when(userDAO.save(user1)).thenReturn(user1);


     }

    @Test
   public void deleteById() {
        String id="a@a";
        User user=new User();
        when(entityManager.createQuery("delete from User where email=:id")).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);


    }
}