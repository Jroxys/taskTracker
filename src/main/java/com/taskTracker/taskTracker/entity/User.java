package com.taskTracker.taskTracker.entity;


import com.taskTracker.taskTracker.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;


import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@SQLRestriction(value = "deleted_at IS NULL")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "userName")
    private String userName;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
