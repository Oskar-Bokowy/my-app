package com.example.my_app.mapper;

import com.example.my_app.dto.request.TeacherRequest;
import com.example.my_app.dto.response.TeacherResponse;
import com.example.my_app.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    public TeacherResponse toResponse(Teacher teacher) {
        return TeacherResponse.builder()
                .teacherId(teacher.getId())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .email(teacher.getEmail())
                .phoneNr(teacher.getPhoneNr())
                .build();
    }

    public Teacher toEntity(TeacherRequest teacherRequest) {
        return Teacher.builder()
                .name(teacherRequest.name())
                .surname(teacherRequest.surname())
                .email(teacherRequest.email())
                .phoneNr(teacherRequest.phoneNr())
                .build();
    }
}
