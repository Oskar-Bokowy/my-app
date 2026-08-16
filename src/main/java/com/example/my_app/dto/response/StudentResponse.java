package com.example.my_app.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StudentResponse(
    Long id,
    String name,
    String surname,
    String email,
    String phoneNr,
    LocalDate dateOfBirth
    ){
}
