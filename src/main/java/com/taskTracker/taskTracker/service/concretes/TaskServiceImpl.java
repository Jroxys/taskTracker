package com.taskTracker.taskTracker.service.concretes;

import com.taskTracker.taskTracker.entity.Task;
import com.taskTracker.taskTracker.entity.TaskStatus;
import com.taskTracker.taskTracker.entity.User;
import com.taskTracker.taskTracker.repository.TaskRepository;
import com.taskTracker.taskTracker.service.abstracts.TaskService;
import com.taskTracker.taskTracker.service.dtos.requests.task.CreateTaskRequest;
import com.taskTracker.taskTracker.service.dtos.requests.task.UpdateTaskRequest;
import com.taskTracker.taskTracker.service.dtos.responses.task.CreatedTaskResponse;
import com.taskTracker.taskTracker.service.dtos.responses.task.DeletedTaskResponse;
import com.taskTracker.taskTracker.service.dtos.responses.task.GetListTaskResponse;
import com.taskTracker.taskTracker.service.dtos.responses.task.UpdatedTaskResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.UpdateUserResponse;
import com.taskTracker.taskTracker.service.mappers.TaskMapper;
import com.taskTracker.taskTracker.service.rules.TaskBusinessRules;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskBusinessRules rules;
    public TaskServiceImpl(TaskRepository taskRepository, TaskBusinessRules rules) {
        this.taskRepository = taskRepository;
        this.rules = rules;
    }
    @Override
    public CreatedTaskResponse add(CreateTaskRequest request) {
        rules.checkIfStatusValid(TaskStatus.valueOf(request.status().toUpperCase()));
        Task task = TaskMapper.INSTANCE.toEntity(request);
        Task savedTask = taskRepository.save(task);
        CreatedTaskResponse response = TaskMapper.INSTANCE.toResponse(savedTask);
        return response;
    }
    @Override
    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }
    @Override
    public DeletedTaskResponse softDelete(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Task not found"));
        task.setDeletedAt(LocalDateTime.now());
        Task deletedTask = taskRepository.save(task);
        DeletedTaskResponse response = TaskMapper.INSTANCE.deletedTaskResponse(deletedTask);
        return response;
    }
    @Override
    public List<GetListTaskResponse> findAll(){
        List<Task> tasks = taskRepository.findAll();
        List<GetListTaskResponse> responses = tasks.stream().
                map(TaskMapper.INSTANCE::toGetListResponse).
                collect(java.util.stream.Collectors.toList());
        return responses;
    }
    @Override
    public GetListTaskResponse findById(Long id){
        Optional<Task> task = taskRepository.findById(id);
        GetListTaskResponse response = null;
        if(task.isPresent()){
            response = TaskMapper.INSTANCE.toGetListResponse(task.get());
        }
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

    @Override
    public UpdatedTaskResponse update(UpdateTaskRequest request) {
        Task task  = taskRepository.findById(request.id())
                .orElseThrow(()->new RuntimeException("Task not found"));
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setUser(request.user());
        task.setStatus(request.status());
        taskRepository.save(task);
        UpdatedTaskResponse response = TaskMapper.INSTANCE.toUpdateResponse(task);
        return response;


    }
}
