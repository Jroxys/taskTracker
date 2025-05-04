package com.taskTracker.taskTracker.repository;

import com.taskTracker.taskTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUserName(String userName);
    void deleteById(Long id);
    List<User> findByEmail(String email);
}
