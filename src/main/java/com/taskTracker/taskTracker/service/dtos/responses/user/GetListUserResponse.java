package com.taskTracker.taskTracker.service.dtos.responses.user;

import com.taskTracker.taskTracker.entity.Task;

public record GetListUserResponse(Long id, String userName, String email){
}
