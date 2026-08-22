package com.example.my_app.exception.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class StudentAlreadyAssignedToClassGroupException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public StudentAlreadyAssignedToClassGroupException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
