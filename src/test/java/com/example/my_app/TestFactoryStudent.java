package com.example.my_app;


import com.example.my_app.dto.request.StudentRequest;
import com.example.my_app.dto.response.StudentResponse;
import com.example.my_app.model.Student;

import java.time.LocalDate;

public class TestFactoryStudent {

    protected static StudentResponse createTestStudentResponse(){
        return StudentResponse.builder()
                .name("oskar")
                .surname("Xyz")
                .email("JaO2@gmai.com")
                .phoneNr("656858989")
                .dateOfBirth(LocalDate.parse("2012-05-15"))
                .build();
    }

    protected static StudentRequest createTestStudentRequest(){
        return StudentRequest.builder()
                .name("oskar")
                .surname("Xyz")
                .email("JaO2@gmai.com")
                .phoneNr("656858989")
                .dateOfBirth(LocalDate.parse("2012-05-15"))
                .build();
    }

    protected static Student createTestStudent(){
        return Student.builder()
                .name("oskar")
                .surname("Xyz")
                .email("JaO2@gmai.com")
                .phoneNr("656858989")
                .dateOfBirth(LocalDate.parse("2012-05-15"))
                .build();
    }
}
