package com.event.core.controller.advice;

import com.event.core.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Error> handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("handleArgumentNotValidException: exception {}", exception.getMessage(), exception);
        return exception.getBindingResult().getAllErrors().stream()
                .map(error -> new Error(error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleEntityNotFoundException(EntityNotFoundException exception) {
        log.error("entityNotFoundException: exception {}", exception.getMessage(), exception);

        return new Error(exception.getMessage());
    }

}
