package com.userstasks.userstasks.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity
public class User {

    @Id
    private String email;
    private String name;
    private String password;
    private float age;

    /*cascade = {
        CascadeType.MERGE,
                CascadeType.REFRESH}
                */

  @OneToMany
  private Collection<Task> tasks=new ArrayList<Task>();

    public User() {
    }

    public User(String name, String email, String password, float age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }


  public Collection<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
