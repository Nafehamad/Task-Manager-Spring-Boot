package com.userstasks.userstasks.dao;

import com.userstasks.userstasks.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;


    @Override
    public User findById(String userEmail) {
        Session session=entityManager.unwrap(Session.class);
        User user= session.get(User.class,userEmail);
        return user;

    }

    @Override
    public List<User> findAll() {
        Session session=entityManager.unwrap(Session.class);
        Query<User> theQuery = session.createQuery("from User", User.class);
        List<User> users=theQuery.getResultList();
        return users;
    }

    @Override
    public User save(User user) {

        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(user);
        return user;

    }

    @Override
    public void deleteById(String userEmail) {

        Session session = entityManager.unwrap(Session.class);
        Query theQuery = session.createQuery("delete from User where email=:id");
        theQuery.setParameter("id", userEmail);
        theQuery.executeUpdate();
    }

}
