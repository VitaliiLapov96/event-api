package com.event.core.controller.advice;

import com.event.core.exception.EntityNotFoundException;
import com.event.core.model.ErrorType;
import com.event.core.model.EventServiceError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public EventServiceError handleEntityNotFoundException(EntityNotFoundException exception) {
        log.error("entityNotFoundException: exception {}", exception.getMessage(), exception);

        return new EventServiceError().setMessage(exception.getMessage())
                .setHttpStatus(HttpStatus.NOT_FOUND)
                .setLocalDateTime(LocalDateTime.now())
                .setErrorType(ErrorType.ENTITY_NOT_FOUND);
    }

}
