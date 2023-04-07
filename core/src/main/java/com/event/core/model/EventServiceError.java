package com.event.core.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class EventServiceError {

    private String message;

    private LocalDateTime localDateTime;
    private HttpStatus httpStatus;
    private ErrorType errorType;

}