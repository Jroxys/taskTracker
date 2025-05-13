package com.taskTracker.taskTracker.common.exceptions.types;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
