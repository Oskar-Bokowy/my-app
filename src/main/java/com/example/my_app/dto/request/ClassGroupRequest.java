package com.example.my_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ClassGroupRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Lvl is required")
        String lvl
) {
}
