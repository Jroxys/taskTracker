package com.taskTracker.taskTracker.service.concretes;

import com.taskTracker.taskTracker.entity.User;
import com.taskTracker.taskTracker.repository.UserRepository;
import com.taskTracker.taskTracker.service.abstracts.UserService;
import com.taskTracker.taskTracker.service.dtos.requests.user.CreateUserRequest;
import com.taskTracker.taskTracker.service.dtos.requests.user.UpdateUserRequest;
import com.taskTracker.taskTracker.service.dtos.responses.user.CreatedUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.DeletedUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.GetListUserResponse;
import com.taskTracker.taskTracker.service.dtos.responses.user.UpdateUserResponse;
import com.taskTracker.taskTracker.service.mappers.UserMapper;
import com.taskTracker.taskTracker.service.rules.UserBusinessRules;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserBusinessRules rules;

    public UserServiceImpl(UserRepository userRepository, UserBusinessRules rules) {
        this.userRepository = userRepository;
        this.rules= rules;
    }
    @Override
    public CreatedUserResponse add(CreateUserRequest request) {
      rules.checkIfEmailExists(request.email());

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
        rules.checkIfIdExists(id);

        userRepository.deleteById(id);
    }
    @Override
    public List<GetListUserResponse> findById(Long id){
        rules.checkIfIdExists(id);

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
        rules.checkIfIdExists(updateUserRequest.id());
        rules.checkIfEmailAlreadyUsedByAnotherUser(updateUserRequest.email(),updateUserRequest.id());
        User user = userRepository.findById(updateUserRequest.id())
                .orElseThrow(()->new RuntimeException("User not found"));
        user.setUserName(updateUserRequest.userName());
        user.setEmail(updateUserRequest.email());

        userRepository.save(user);
        UpdateUserResponse response = UserMapper.INSTANCE.toUpdateResponse(user);
        return response;
    }

    @Override
    public GetListUserResponse findByEmail(String email){
        User user  = userRepository.findByEmail(email);
        GetListUserResponse response = UserMapper.INSTANCE.toGetListResponse(user);
        return response;
    }
    @Override
    public DeletedUserResponse softDelete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found"));
        user.setDeletedAt(LocalDateTime.now());
        User deletedUser = userRepository.save(user);
        DeletedUserResponse response = UserMapper.INSTANCE.deletedUserResponse(deletedUser);
        return response;


    }
}
