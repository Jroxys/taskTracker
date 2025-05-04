package com.taskTracker.taskTracker.service.mappers;

import com.taskTracker.taskTracker.entity.Task;
import com.taskTracker.taskTracker.service.dtos.requests.task.CreateTaskRequest;
import com.taskTracker.taskTracker.service.dtos.responses.task.CreatedTaskResponse;
import com.taskTracker.taskTracker.service.dtos.responses.task.GetListTaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "user.id",source = "userId")
    Task toEntity(CreateTaskRequest request);
    @Mapping(source = "user",target = "user")
    CreatedTaskResponse toResponse(Task task);
    @Mapping(source = "user.userName",target = "userName")
    GetListTaskResponse toGetListResponse(Task task);

}
