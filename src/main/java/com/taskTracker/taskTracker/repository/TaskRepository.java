package com.taskTracker.taskTracker.repository;

import com.taskTracker.taskTracker.entity.Task;
import com.taskTracker.taskTracker.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findTaskByTitle(String title);
    List<Task> findTaskByStatus(TaskStatus status);
    void deleteById(Long id);
    @Modifying
    @Query("UPDATE Task t SET t.deletedAt = CURRENT_TIMESTAMP WHERE t.id = :id")
    Task softDelete(Long id);

}
