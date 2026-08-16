package com.example.my_app.dto.request;

import com.example.my_app.model.LessonDuration;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LessonRequest(
        @NotNull(message = "Start date is required")
        LocalDateTime start,
        @NotNull(message = "Duration is required")
        LessonDuration duration,
        @NotBlank(message = "Lesson topic is required")
        String lessonTopic,
        @NotNull(message = "Class group is required")
        Long classGroupId
) {
}
