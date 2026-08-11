package com.example.my_app.dto.response;

import com.example.my_app.model.LessonDuration;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LessonResponse (
        Long lessonId,
        LocalDateTime start,
        LessonDuration duration,
        Long classGroupId
) {
}
