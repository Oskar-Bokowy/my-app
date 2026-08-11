package com.example.my_app.dto.request;

import com.example.my_app.model.LessonDuration;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LessonCreateRequest(
        @NotNull(message = "Start date is required")
        LocalDateTime start,
        @NotNull(message = "Duration is required")
        LessonDuration duration,
        @NotNull(message = "Class group is required")
        Long classGroupId
) {
}
