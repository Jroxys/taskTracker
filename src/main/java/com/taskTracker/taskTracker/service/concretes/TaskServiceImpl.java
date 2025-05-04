package com.taskTracker.taskTracker.service.concretes;

import com.taskTracker.taskTracker.entity.Task;
import com.taskTracker.taskTracker.entity.TaskStatus;
import com.taskTracker.taskTracker.repository.TaskRepository;
import com.taskTracker.taskTracker.service.abstracts.TaskService;
import com.taskTracker.taskTracker.service.dtos.requests.task.CreateTaskRequest;
import com.taskTracker.taskTracker.service.dtos.responses.task.CreatedTaskResponse;
import com.taskTracker.taskTracker.service.dtos.responses.task.GetListTaskResponse;
import com.taskTracker.taskTracker.service.mappers.TaskMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public CreatedTaskResponse add(CreateTaskRequest request) {
        Task task = TaskMapper.INSTANCE.toEntity(request);
        Task savedTask = taskRepository.save(task);
        CreatedTaskResponse response = TaskMapper.INSTANCE.toResponse(savedTask);
        return response;
    }
    @Override
    public List<GetListTaskResponse> findTaskByTitle(String title){
        List<Task> tasks = taskRepository.findTaskByTitle(title);
        List<GetListTaskResponse> responses = tasks.stream().
                map(TaskMapper.INSTANCE::toGetListResponse).
                collect(java.util.stream.Collectors.toList());
        return responses;
    }
    @Override
    public List<GetListTaskResponse> findTaskByStatus(TaskStatus status){
        List<Task> tasks = taskRepository.findTaskByStatus(status);
        List<GetListTaskResponse> responses = tasks.stream().
                map(TaskMapper.INSTANCE::toGetListResponse).
                collect(java.util.stream.Collectors.toList());
        return responses;
    }
}
