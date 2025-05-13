package com.taskTracker.taskTracker.service.dtos.requests.task;

import com.taskTracker.taskTracker.entity.TaskStatus;
import com.taskTracker.taskTracker.entity.User;

public record UpdateTaskRequest(Long id, User user  ,String title, String description, TaskStatus status) {
}
