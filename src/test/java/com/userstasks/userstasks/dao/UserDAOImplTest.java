package com.userstasks.userstasks.dao;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.userstasks.userstasks.dao.UserDAOImpl;
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
public  class UserDAOImplTest {

    @Mock
    UserDAO userDAO;
    @Mock
    UserDAOImpl userDAOImpl;


    @Test
   public void findById() {
        User user=new User();
        when(userDAO.findById("sam@gmail.com")).thenReturn( user);
        Assert.assertEquals("",userDAOImpl.findById("sam@gmail.com"),null);
        Assert.assertNull(userDAOImpl.findById("sam@gmail.com"));

    }

    @Test
   public void findAll() {

        List<User> users = new ArrayList<User>();
        when(userDAO.findAll()).thenReturn(users);
        Assert.assertEquals(userDAOImpl.findAll(),users);
    }

    @Test
  public   void save() {
        User user=new User();
        userDAO.save(user);
        when(userDAO.save(user)).thenReturn(user);
    }

    @Test
   public void deleteById() {

        userDAO.deleteById("sam@gmail.com");
        verify(userDAO).deleteById("sam@gmail.com");

    }
}