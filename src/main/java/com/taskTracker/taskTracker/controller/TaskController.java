package com.taskTracker.taskTracker.controller;

import com.taskTracker.taskTracker.entity.Task;
import com.taskTracker.taskTracker.entity.TaskStatus;
import com.taskTracker.taskTracker.service.abstracts.TaskService;
import com.taskTracker.taskTracker.service.dtos.requests.task.CreateTaskRequest;
import com.taskTracker.taskTracker.service.dtos.responses.task.CreatedTaskResponse;
import com.taskTracker.taskTracker.service.dtos.responses.task.DeletedTaskResponse;
import com.taskTracker.taskTracker.service.dtos.responses.task.GetListTaskResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.GetListUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedTaskResponse add(@RequestBody CreateTaskRequest request){
        return taskService.add(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListTaskResponse> findAll(){
        return taskService.findAll();
    }
    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListTaskResponse> findTaskByTitleContaining(@PathVariable String title){
        return taskService.findTaskByTitle(title);
    }
    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetListTaskResponse findById(@PathVariable Long id){
        return taskService.findById(id);
    }
    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListTaskResponse> findTaskByStatus(@PathVariable TaskStatus status){
        return taskService.findTaskByStatus(status);
    }



    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        taskService.deleteById(id);
    }
    @DeleteMapping("/softdelete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeletedTaskResponse softDelete(@RequestParam Long id){
        return taskService.softDelete(id);
    }
}
