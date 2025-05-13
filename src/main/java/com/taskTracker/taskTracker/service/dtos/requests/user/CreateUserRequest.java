package com.taskTracker.taskTracker.service.dtos.requests.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "User name is requested.")
        @Size(min = 3, max = 20, message = "User name must be between 3 and 20 characters.")
        String userName,
        @NotBlank(message = "Email is requested.")
        @Email(message = "Email is invalid.")
        String email) {
}
