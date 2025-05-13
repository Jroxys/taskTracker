package com.taskTracker.taskTracker.common.exceptions.details;

import org.springframework.http.HttpStatus;

public class InvalidTaskStatusProblemDetails extends ProblemDetails {
    public InvalidTaskStatusProblemDetails(){
        setStatus(HttpStatus.BAD_REQUEST.toString());
        setTitle("Invalid Task Status");
        setType("http://acunmedya.com/exceptions/invalidtaskstatus");
    }
}
