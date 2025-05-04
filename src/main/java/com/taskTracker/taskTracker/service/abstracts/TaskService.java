package com.taskTracker.taskTracker.service.abstracts;

import com.taskTracker.taskTracker.entity.TaskStatus;
import com.taskTracker.taskTracker.service.dtos.requests.task.CreateTaskRequest;
import com.taskTracker.taskTracker.service.dtos.responses.task.CreatedTaskResponse;
import com.taskTracker.taskTracker.service.dtos.responses.task.GetListTaskResponse;

import java.util.List;

public interface TaskService {
    CreatedTaskResponse add(CreateTaskRequest request);
    List<GetListTaskResponse> findTaskByTitle(String title);
    List<GetListTaskResponse> findTaskByStatus(TaskStatus status);
}
