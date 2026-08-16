package com.example.my_app.mapper;

import com.example.my_app.dto.request.LessonRequest;
import com.example.my_app.dto.response.LessonResponse;
import com.example.my_app.model.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

    public LessonResponse toResponse(Lesson lesson) {
        return LessonResponse.builder()
                .lessonId(lesson.getLessonId())
                .lessonTopic(lesson.getLessonTopic())
                .start(lesson.getStart())
                .duration(lesson.getLessonDuration())
                .classGroupId(lesson.getClassGroup().getId())
                .build();
    }

    public Lesson toEntity(LessonRequest lessonRequest) {
        return Lesson.builder()
                .lessonTopic(lessonRequest.lessonTopic())
                .start(lessonRequest.start())
                .lessonDuration(lessonRequest.duration())
                .build();
    }
}
