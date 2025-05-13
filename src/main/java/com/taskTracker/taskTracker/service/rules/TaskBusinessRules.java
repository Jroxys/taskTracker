package com.taskTracker.taskTracker.service.rules;

import com.taskTracker.taskTracker.common.exceptions.types.BusinessException;
import com.taskTracker.taskTracker.entity.TaskStatus;
import com.taskTracker.taskTracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskBusinessRules {
    private  final TaskRepository taskRepository;
    public TaskBusinessRules(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public void checkIfStatusValid(TaskStatus status) {
        if (!status.equals("PENDING") && !status.equals("IN_PROGRESS") && !status.equals("COMPLETED")) {
            throw new BusinessException("Task status must be PENDING, IN_PROGRESS or COMPLETED");
        }
    }
}
