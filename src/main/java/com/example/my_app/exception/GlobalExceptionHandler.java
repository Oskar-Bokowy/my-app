package com.example.my_app.exception;


import com.example.my_app.exception.exception.EntityNotFoundException;
import com.example.my_app.exception.exception.StudentAlreadyAssignedToClassGroupException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private static final Logger log = createLogger();

    private static Logger createLogger() {
        return LoggerFactory.getLogger(GlobalExceptionHandler.class);
    }

    @ExceptionHandler(StudentAlreadyAssignedToClassGroupException.class)
    public ResponseEntity<ErrorMessage> handleStudentAlreadyAssigned(StudentAlreadyAssignedToClassGroupException ex) {
        ErrorMessage error = new ErrorMessage(
                ex.getMessage(),
                LocalDateTime.now(),
                ex.getHttpStatus()
        );
        return ResponseEntity.status(error.getHttpStatus()).body(error);
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


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage error = new ErrorMessage(
                returnDefaultMessageFromValidationException(ex.getMessage()),
                LocalDateTime.now(),
                status
        );
        return ResponseEntity.status(status).body(error);
    }

    private static String returnDefaultMessageFromValidationException(String message) {
        Pattern pattern = Pattern.compile("default message \\[(.*?)]");
        Matcher matcher = pattern.matcher(message);

        String result = "";
        while (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }

}
