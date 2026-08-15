package com.example.my_app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TeacherRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Surname is required")
        String surname,
        @Email(message = "Email is required")
        String email,
        @NotBlank(message = "Phone nr is required")
        String phoneNr
) {
}
