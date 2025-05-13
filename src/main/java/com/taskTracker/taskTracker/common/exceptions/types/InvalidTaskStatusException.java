package com.taskTracker.taskTracker.common.exceptions.types;

public class InvalidTaskStatusException extends RuntimeException {
    public InvalidTaskStatusException(String message) {
        super(message);
    }
}
