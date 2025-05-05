package com.taskTracker.taskTracker.common.exceptions.details;

import org.springframework.http.HttpStatus;

public class NotFoundProblemDetails extends ProblemDetails {
    public NotFoundProblemDetails(){
        setTitle("Not Found");
        setType("http://acunmedya.com/exceptions/notfound");
        setStatus(HttpStatus.NOT_FOUND.toString());
    }
}
