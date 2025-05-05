package com.taskTracker.taskTracker.common.exceptions.types;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
