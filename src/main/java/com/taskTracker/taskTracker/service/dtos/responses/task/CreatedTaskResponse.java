package com.taskTracker.taskTracker.service.dtos.responses.task;

import com.taskTracker.taskTracker.entity.TaskStatus;
import com.taskTracker.taskTracker.service.dtos.responses.user.GetListUserResponse;

public record CreatedTaskResponse(Long id, String title, String description, TaskStatus status, GetListUserResponse user) {
}
