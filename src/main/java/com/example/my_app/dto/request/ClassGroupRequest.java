package com.example.my_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ClassGroupRequest(
        @NotNull(message = "Teacher is required")
        Long teacherId,
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Lvl is required")
        String lvl
) {
}
