package com.taskTracker.taskTracker.common.exceptions.handlers;

import com.taskTracker.taskTracker.common.exceptions.details.BusinessProblemDetails;
import com.taskTracker.taskTracker.common.exceptions.details.InternalServerProblemDetails;
import com.taskTracker.taskTracker.common.exceptions.details.NotFoundProblemDetails;
import com.taskTracker.taskTracker.common.exceptions.types.BusinessException;
import com.taskTracker.taskTracker.common.exceptions.types.InternalServerException;
import com.taskTracker.taskTracker.common.exceptions.types.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleBusinessException(BusinessException businessException){
        BusinessProblemDetails details = new BusinessProblemDetails();
        details.setDetail(businessException.getMessage());
        return details;
    }
    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundProblemDetails handleNotFoundException(NotFoundException notFoundException){
        NotFoundProblemDetails details = new NotFoundProblemDetails();
        details.setDetail(notFoundException.getMessage());
        return details;
    }

    @ExceptionHandler({InternalServerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public InternalServerProblemDetails handleInternalServerException(InternalServerException internalServerException){
        InternalServerProblemDetails details = new InternalServerProblemDetails();
        details.setDetail(internalServerException.getMessage());
        return details;
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        BusinessProblemDetails details = new BusinessProblemDetails();
        details.setDetail("Veri bütünlüğü hatası: " + exception.getMessage());
        return details;
    }
}
