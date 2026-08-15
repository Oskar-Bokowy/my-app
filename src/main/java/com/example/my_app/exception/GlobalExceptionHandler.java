package com.example.my_app.exception;


import com.example.my_app.exception.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private static final Logger log = createLogger();

    private static Logger createLogger() {
        return LoggerFactory.getLogger(GlobalExceptionHandler.class);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorMessage error = new ErrorMessage(
                ex.getMessage(),
                LocalDateTime.now(),
                ex.getHttpStatus()
        );
        return ResponseEntity.status(error.getHttpStatus()).body(error);
    }

}
