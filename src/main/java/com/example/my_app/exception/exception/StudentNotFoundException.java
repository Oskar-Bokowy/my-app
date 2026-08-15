package com.example.my_app.exception.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StudentNotFoundException extends EntityNotFoundException {
    public StudentNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
