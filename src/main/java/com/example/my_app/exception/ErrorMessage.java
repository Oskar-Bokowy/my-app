package com.example.my_app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorMessage {
    private String message;
    private LocalDateTime localDateTime;
    private HttpStatus httpStatus;
}
