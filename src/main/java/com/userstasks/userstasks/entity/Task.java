package com.userstasks.userstasks.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;
    private String description;
    private boolean completed;

   /* @ManyToOne(fetch = FetchType.EAGER,cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH} )
    @JoinTable(name = "User_Task",joinColumns=@JoinColumn(name = "Task_id"),
            inverseJoinColumns = @JoinColumn(name = "User_Email")
    )*/
   @ManyToOne(fetch = FetchType.EAGER,
           cascade = CascadeType.ALL)
   @JoinColumn(name = "User_Email")
    private User user;

    public Task() {
    }

    public Task(int id, String description, boolean completed) {
        this.id = id;
        this.description = description;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}
