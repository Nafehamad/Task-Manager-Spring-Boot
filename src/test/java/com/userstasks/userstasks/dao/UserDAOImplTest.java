package com.userstasks.userstasks.dao;



import static org.junit.jupiter.api.Assertions.*;
import com.userstasks.userstasks.entity.User;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;
import org.hibernate.query.Query;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOImplTest {



    @Mock
    EntityManager entityManager;
    @Mock
    Query<User> query;
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
          when(entityManager.unwrap(Session.class)).thenReturn(session);
          when(session.get(User.class,"a@a")).thenReturn(user);
          userDAOImpl.findById("a@a");
          assertEquals(userDAOImpl.findById("a@a").getName(),"a");
          assertEquals(userDAOImpl.findById("a@a").getPassword(),"a");
          assertEquals(userDAOImpl.findById("a@a").getAge(),10);



    }

   @Test
   public void findAll() {

       User user=new User("a","a@a","a",10);
       User user1=new User("","a@a","a",10);
       userList.add(user);
       userList.add(user1);
       when(entityManager.unwrap(Session.class)).thenReturn(session);
       when(session.createQuery(any(String.class), eq(User.class))).thenReturn(query);
       when(query.setParameter(any(String.class), any(String.class))).thenReturn(query);
       when(query.getResultList()).thenReturn(userList);
       Assert.assertEquals(userDAOImpl.findAll().size(),2);

    }

   @Test
  public void save() {

       User user=new User("a","a@a","a",10);
       when(entityManager.unwrap(Session.class)).thenReturn(session);
       userDAOImpl.save(user);

     }

    @Test
   public void deleteById() {
        String id="a@a";
        User user=new User("a",id,"a",10);
        when(entityManager.unwrap(Session.class)).thenReturn(session);
        when(session.createQuery("delete from User where email=:id")).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);
        userDAOImpl.deleteById(id);


    }
}