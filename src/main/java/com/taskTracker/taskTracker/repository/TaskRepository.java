package com.taskTracker.taskTracker.repository;

import com.taskTracker.taskTracker.entity.Task;
import com.taskTracker.taskTracker.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findTaskByTitle(String title);
    List<Task> findTaskByStatus(TaskStatus status);

}
