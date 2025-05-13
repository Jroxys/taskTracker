package com.taskTracker.taskTracker.controller;

import com.taskTracker.taskTracker.entity.Task;
import com.taskTracker.taskTracker.service.abstracts.UserService;
import com.taskTracker.taskTracker.service.dtos.requests.user.CreateUserRequest;
import com.taskTracker.taskTracker.service.dtos.requests.user.UpdateUserRequest;
import com.taskTracker.taskTracker.service.dtos.responses.user.CreatedUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.DeletedUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.GetListUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.UpdateUserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedUserResponse add(@RequestBody @Valid CreateUserRequest request){
        return userService.add(request);
    }
    @GetMapping("/username/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListUserResponse> findByUserName(@PathVariable String userName){
        return userService.findByUserName(userName);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        userService.deleteById(id);
    }
    @DeleteMapping("/softdelete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeletedUserResponse softDelete(@PathVariable Long id){
        return userService.softDelete(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateUserResponse update(@PathVariable Long id, @RequestBody UpdateUserRequest request){
            UpdateUserRequest  newRequest = new UpdateUserRequest(id,request.userName() , request.email());
        return userService.update(newRequest);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListUserResponse> findAll(){
        return userService.findAll();
    }
    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public GetListUserResponse findByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }

}
