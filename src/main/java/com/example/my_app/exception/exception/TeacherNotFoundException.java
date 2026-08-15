package com.example.my_app.exception.exception;

import org.springframework.http.HttpStatus;

public class TeacherNotFoundException extends EntityNotFoundException {
    public TeacherNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
