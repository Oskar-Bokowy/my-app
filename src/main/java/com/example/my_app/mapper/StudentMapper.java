package com.example.my_app.mapper;

import com.example.my_app.dto.request.StudentRequest;
import com.example.my_app.dto.response.StudentResponse;
import com.example.my_app.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentResponse toResponse(Student student){
        return StudentResponse.builder()
                .studentId(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .email(student.getEmail())
                .dateOfBirth(student.getDateOfBirth())
                .phoneNr(student.getPhoneNr())
                .build();
    }

    public Student toEntity(StudentRequest studentRequest){
        return Student.builder()
                .name(studentRequest.name())
                .surname(studentRequest.surname())
                .dateOfBirth(studentRequest.dateOfBirth())
                .email(studentRequest.email())
                .phoneNr(studentRequest.phoneNr())
                .build();
    }
}
