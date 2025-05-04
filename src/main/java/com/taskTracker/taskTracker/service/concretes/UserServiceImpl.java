package com.taskTracker.taskTracker.service.concretes;

import com.taskTracker.taskTracker.entity.User;
import com.taskTracker.taskTracker.repository.UserRepository;
import com.taskTracker.taskTracker.service.abstracts.UserService;
import com.taskTracker.taskTracker.service.dtos.requests.user.CreateUserRequest;
import com.taskTracker.taskTracker.service.dtos.requests.user.UpdateUserRequest;
import com.taskTracker.taskTracker.service.dtos.responses.user.CreatedUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.GetListUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.UpdateUserResponse;
import com.taskTracker.taskTracker.service.mappers.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public CreatedUserResponse add(CreateUserRequest request) {
      User user = UserMapper.INSTANCE.toEntity(request);
      User savedUser = userRepository.save(user);
      CreatedUserResponse response = UserMapper.INSTANCE.toResponse(savedUser);
      return response;
    };

    @Override
    public List<GetListUserResponse> findByUserName(String userName){
      List<User> users = userRepository.findByUserName(userName);
      List<GetListUserResponse> responses = users.stream().
              map(UserMapper.INSTANCE::toGetListResponse).
              collect(Collectors.toList());
      return responses;
    };
    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("No user found by: ID " + id);
        }
        userRepository.deleteById(id);
    }
    @Override
    public List<GetListUserResponse> findById(Long id){
        Optional<User> users = userRepository.findById(id);
        List<GetListUserResponse> responses = new ArrayList<>();

        users.ifPresent(user -> {
            // If a user is found, map it to the response and add it to the list
            GetListUserResponse response = UserMapper.INSTANCE.toGetListResponse(user);
            responses.add(response);
        });

        return responses;
    }
    @Override
    public List<GetListUserResponse> findAll(){
        List<User> users = userRepository.findAll();
        List<GetListUserResponse> responses = users.stream().
                map(UserMapper.INSTANCE::toGetListResponse).
                collect(Collectors.toList());
        return responses;
    }

    @Override
    public UpdateUserResponse update(UpdateUserRequest updateUserRequest){
        User user = userRepository.findById(updateUserRequest.id())
                .orElseThrow(()->new RuntimeException("User not found"));
        user.setUserName(updateUserRequest.userName());
        user.setEmail(updateUserRequest.email());

        userRepository.save(user);
        UpdateUserResponse response = UserMapper.INSTANCE.toUpdateResponse(user);
        return response;
    }

    @Override
    public List<GetListUserResponse> findByEmail(String email){
        List<User> users = userRepository.findByEmail(email);
        List<GetListUserResponse> responses = users.stream().
                map(UserMapper.INSTANCE::toGetListResponse).
                collect(Collectors.toList());
        return responses;
    }
}
