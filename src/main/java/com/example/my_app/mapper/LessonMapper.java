package com.example.my_app.mapper;

import com.example.my_app.dto.request.LessonCreateRequest;
import com.example.my_app.dto.response.LessonResponse;
import com.example.my_app.model.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

    public LessonResponse toResponse(Lesson lesson) {
        return LessonResponse.builder()
                .lessonId(lesson.getId())
                .start(lesson.getStart())
                .duration(lesson.getLessonDuration())
                .classGroupId(lesson.getClassGroup().getId())
                .build();
    }

    public Lesson toEntity(LessonCreateRequest lessonCreateRequest) {
        return Lesson.builder()
                .start(lessonCreateRequest.start())
                .lessonDuration(lessonCreateRequest.duration())
                .build();
    }
}
