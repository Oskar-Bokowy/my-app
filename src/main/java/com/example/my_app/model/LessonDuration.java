package com.example.my_app.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public enum LessonDuration {
    MIN_45(45),
    MIN_60(60),
    MIN_90(90);
    private final int minutes;

    LessonDuration(int minutes) {
        this.minutes = minutes;
    }

}
