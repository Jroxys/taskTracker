package com.taskTracker.taskTracker.service.abstracts;
import com.taskTracker.taskTracker.entity.User;
import com.taskTracker.taskTracker.service.dtos.requests.user.CreateUserRequest;
import com.taskTracker.taskTracker.service.dtos.requests.user.UpdateUserRequest;
import com.taskTracker.taskTracker.service.dtos.responses.user.CreatedUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.DeletedUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.GetListUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.UpdateUserResponse;

import java.util.List;

public interface UserService {
    CreatedUserResponse add(CreateUserRequest request);
    void deleteById(Long id);
    List<GetListUserResponse> findAll();
    List<GetListUserResponse> findByUserName(String userName);
    GetListUserResponse findByEmail(String email);
    List<GetListUserResponse> findById(Long id);
    UpdateUserResponse update(UpdateUserRequest request);
    DeletedUserResponse softDelete(Long id);


}
