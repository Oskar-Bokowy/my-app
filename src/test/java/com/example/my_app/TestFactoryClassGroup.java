package com.example.my_app;

import com.example.my_app.dto.request.ClassGroupRequest;
import com.example.my_app.dto.response.ClassGroupResponse;
import com.example.my_app.model.ClassGroup;

public class TestFactoryClassGroup {

    protected static ClassGroup createTestClassGroup(){
        return ClassGroup.builder()
                .name("Poziomki")
                .lvl("A2")
                .build();
    }

    protected static ClassGroupResponse createTestClassGroupResponse(){
        return ClassGroupResponse.builder()
                .name("Poziomki")
                .lvl("A2")
                .build();
    }

    protected static ClassGroupRequest createTestClassGroupRequest(){
        return ClassGroupRequest.builder()
                .name("Poziomki")
                .lvl("A2")
                .build();
    }
}
