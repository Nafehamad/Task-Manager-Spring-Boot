
package com.userstasks.userstasks.service;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.userstasks.userstasks.dao.UserDAOImpl;
import com.userstasks.userstasks.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserDAOImpl userDAO;
    @InjectMocks
    UserServiceImpl userServiceImpl;


    @Test
    public void findById() {

        User user=new User();
        when(userDAO.findById("sam@gmail.com")).thenReturn( user);
        Assert.assertEquals(userServiceImpl.findById("sam@gmail.com"),user);
        Assert.assertEquals (userServiceImpl.findById("sam@gmail.com").getAge(),2.0,24.0);
        Assert.assertEquals (userServiceImpl.findById("sam@gmail.com").getName(),"","");
        Assert.assertEquals (userServiceImpl.findById("sam@gmail.com").getPassword(),"","");
        Assert.assertNotNull(userServiceImpl.findById("sam@gmail.com"));

    }

    @Test
    public void findAll() {
        List<User> users = new ArrayList<User>();
        when(userDAO.findAll()).thenReturn(users);
        Assert.assertEquals(userServiceImpl.findAll().size(),0);
    }

    @Test
    public void save() {
       User user=new User();
       userDAO.save(user);
       when(userDAO.save(user)).thenReturn(user);
    }

    @Test
    public void deleteById() {
        User user=new User();
        User user1=new User();
        userServiceImpl.deleteById("sam@gmail.com");
        verify(userDAO).deleteById("sam@gmail.com");

    }

   }