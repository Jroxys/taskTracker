package com.taskTracker.taskTracker.service.mappers;

import com.taskTracker.taskTracker.entity.User;
import com.taskTracker.taskTracker.service.dtos.requests.user.CreateUserRequest;
import com.taskTracker.taskTracker.service.dtos.responses.user.CreatedUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.DeletedUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.GetListUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.UpdateUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "userName",target = "userName")
    @Mapping(source = "email",target = "email")
    UpdateUserResponse toUpdateResponse(User user);
    User toEntity(CreateUserRequest request);
    CreatedUserResponse toResponse(User user);
    GetListUserResponse toGetListResponse(User user);
    DeletedUserResponse deletedUserResponse(User user);
}
