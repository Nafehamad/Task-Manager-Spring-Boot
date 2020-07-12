package com.userstasks.userstasks.dao;

import com.userstasks.userstasks.entity.Task;
import com.userstasks.userstasks.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {

    @Autowired
    private EntityManager entityManager;


    @Override
    public Task findById(int taskId) {

        Session session=entityManager.unwrap(Session.class);

        Task task= session.get(Task.class,taskId);
        return task;

    }

    @Override
    public List<Task> findAll() {

        Session session=entityManager.unwrap(Session.class);
        Query<Task> theQuery = session.createQuery("from Task", Task.class);
        List<Task> tasks=theQuery.getResultList();
        return tasks;

    }

    @Override
    public Task save(Task task) {

        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(task);
        return task;
    }

    @Override
    public void deleteById(int taskId) {

        Session session = entityManager.unwrap(Session.class);
        Query theQuery = session.createQuery("delete from Task where id=:id");
        theQuery.setParameter("id", taskId);
        theQuery.executeUpdate();

    }
}
