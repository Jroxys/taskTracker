package com.taskTracker.taskTracker.service.dtos.responses.task;

import com.taskTracker.taskTracker.entity.TaskStatus;
import com.taskTracker.taskTracker.entity.User;

public record UpdatedTaskResponse(String title, User user , String description, TaskStatus status) {
}
