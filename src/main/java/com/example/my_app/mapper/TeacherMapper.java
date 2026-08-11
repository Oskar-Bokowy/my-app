package com.example.my_app.mapper;

import com.example.my_app.dto.request.TeacherCreateRequest;
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

    public Teacher toEntity(TeacherCreateRequest teacherCreateRequest) {
        return Teacher.builder()
                .name(teacherCreateRequest.name())
                .surname(teacherCreateRequest.surname())
                .email(teacherCreateRequest.email())
                .phoneNr(teacherCreateRequest.phoneNr())
                .build();
    }
}
