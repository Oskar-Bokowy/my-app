package com.example.my_app.exception.exception;

import org.springframework.http.HttpStatus;

public class LessonNotFoundException extends EntityNotFoundException{

    public LessonNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
