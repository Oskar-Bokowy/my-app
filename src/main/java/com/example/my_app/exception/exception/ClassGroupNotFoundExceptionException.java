package com.example.my_app.exception.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClassGroupNotFoundExceptionException extends EntityNotFoundException {
    public ClassGroupNotFoundExceptionException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

}
