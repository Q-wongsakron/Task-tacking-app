package com.example.tasks_tracking.controller;

import com.example.tasks_tracking.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// tell spring this class handles exception across all of our controller
@ControllerAdvice
public class GlobalExceptionHandler {

    // for specify handle IllegalArgumentException with custom Exception
    // exception handling mechanism spring finds controller advice class and match the exception type
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleExceptions(
            RuntimeException ex, WebRequest request
    ){

        ErrorResponse errorResponse = new ErrorResponse(
                // 400 or bad request
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        // convert error response to json and send to client
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
