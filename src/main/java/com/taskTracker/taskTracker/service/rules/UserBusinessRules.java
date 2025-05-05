package com.taskTracker.taskTracker.service.rules;

import com.taskTracker.taskTracker.common.exceptions.types.BusinessException;
import com.taskTracker.taskTracker.common.exceptions.types.NotFoundException;
import com.taskTracker.taskTracker.entity.User;
import com.taskTracker.taskTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBusinessRules {

    private final UserRepository userRepository;
    public UserBusinessRules(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void checkIfIdExists(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new BusinessException("User not found");
        }
    }
    public void checkIfEmailAlreadyUsedByAnotherUser(String email, Long userId) {
        User user = userRepository.findByEmail(email);

        if (user != null && !user.getId().equals(userId)) {
            throw new BusinessException("Email already used by another user");
        }

    }

    public void checkIfEmailExists(String email) {
        if (userRepository.findByEmail(email) != null) {
            throw new BusinessException("Email already exists");
        }
    }
}
