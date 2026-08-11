package com.example.my_app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StudentCreateRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Surname is required")
        String surname,
        @Email(message = "Email is required")
        String email,
        @NotNull(message = "Date of birth is required")
        LocalDate  dateOfBirth,
        @NotBlank(message = "Phone nr is required")
        String phoneNr
        ) {
}
