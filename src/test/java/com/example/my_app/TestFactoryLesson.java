package com.example.my_app;

import com.example.my_app.dto.request.LessonRequest;
import com.example.my_app.dto.response.LessonResponse;
import com.example.my_app.model.Lesson;
import com.example.my_app.model.LessonDuration;

import java.time.LocalDateTime;

public class TestFactoryLesson {

    protected static Lesson createTestLesson(){
        return Lesson.builder()
                .lessonTopic("Zad 1")
                .start(LocalDateTime.parse("2025-10-06T10:00:00"))
                .lessonDuration(LessonDuration.MIN_45)
                .build();
    }

    protected static LessonResponse createTestLessonResponse(){
        return LessonResponse.builder()
                .lessonTopic("Zad 1")
                .duration(LessonDuration.MIN_45)
                .start(LocalDateTime.parse("2025-10-06T10:00:00"))
                .build();
    }

    protected static LessonRequest createTestLessonRequest(){
        return LessonRequest.builder()
                .lessonTopic("Zad 1")
                .duration(LessonDuration.MIN_45)
                .start(LocalDateTime.parse("2025-10-06T10:00:00"))
                .build();
    }
}
