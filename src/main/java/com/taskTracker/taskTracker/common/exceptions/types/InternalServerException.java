package com.taskTracker.taskTracker.common.exceptions.types;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
