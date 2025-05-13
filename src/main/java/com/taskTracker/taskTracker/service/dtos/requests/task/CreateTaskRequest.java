package com.taskTracker.taskTracker.service.dtos.requests.task;

import com.taskTracker.taskTracker.entity.TaskStatus;

public record CreateTaskRequest(String title, String description, String status, Long userId) {

}
