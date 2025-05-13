package com.taskTracker.taskTracker.service.dtos.responses.task;

import com.taskTracker.taskTracker.entity.TaskStatus;
import com.taskTracker.taskTracker.entity.User;

public record DeletedTaskResponse(Long id , User user , String title , String description , TaskStatus status , Long userId) {
}
