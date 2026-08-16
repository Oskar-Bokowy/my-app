package com.example.my_app;

import com.example.my_app.dto.request.TeacherRequest;
import com.example.my_app.dto.response.TeacherResponse;
import com.example.my_app.model.Teacher;

public class TestFactoryTeacher {

    protected static TeacherResponse createTestTeacherResponse(){
        return TeacherResponse.builder()
                .name("oskar")
                .surname("Xyz")
                .email("JaO2@gmai.com")
                .phoneNr("656858989")
                .build();
    }

    protected static TeacherRequest createTestTeacherRequest(){
        return TeacherRequest.builder()
                .name("oskar")
                .surname("Xyz")
                .email("JaO2@gmai.com")
                .phoneNr("656858989")
                .build();
    }

    protected static Teacher createTestTeacher(){
        return Teacher.builder()
                .name("oskar")
                .surname("Xyz")
                .email("JaO2@gmai.com")
                .phoneNr("656858989")
                .build();
    }
}
