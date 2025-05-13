package com.taskTracker.taskTracker.service.abstracts;

import com.taskTracker.taskTracker.entity.Task;
import com.taskTracker.taskTracker.entity.TaskStatus;
import com.taskTracker.taskTracker.service.dtos.requests.task.CreateTaskRequest;
import com.taskTracker.taskTracker.service.dtos.requests.task.UpdateTaskRequest;
import com.taskTracker.taskTracker.service.dtos.responses.task.CreatedTaskResponse;
import com.taskTracker.taskTracker.service.dtos.responses.task.DeletedTaskResponse;
import com.taskTracker.taskTracker.service.dtos.responses.task.GetListTaskResponse;
import com.taskTracker.taskTracker.service.dtos.responses.task.UpdatedTaskResponse;

import java.util.List;

public interface TaskService {
    CreatedTaskResponse add(CreateTaskRequest request);
    GetListTaskResponse findById(Long id);
    List<GetListTaskResponse> findTaskByTitle(String title);
    List<GetListTaskResponse> findAll();
    List<GetListTaskResponse> findTaskByStatus(TaskStatus status);
    UpdatedTaskResponse update(UpdateTaskRequest request);
    void deleteById(Long id);
    DeletedTaskResponse softDelete(Long id);
}
