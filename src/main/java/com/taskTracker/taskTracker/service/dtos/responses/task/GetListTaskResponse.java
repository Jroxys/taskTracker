package com.taskTracker.taskTracker.service.dtos.responses.task;

import com.taskTracker.taskTracker.entity.TaskStatus;

public record GetListTaskResponse(Long id, String userName, String title, String description, TaskStatus status) {
}
