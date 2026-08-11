package com.example.my_app.dto.response;

import lombok.Builder;

@Builder
public record TeacherResponse (
        Long teacherId,
        String name,
        String surname,
        String email,
        String phoneNr
) {
}
