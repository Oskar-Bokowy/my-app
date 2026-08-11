package com.example.my_app.mapper;

import com.example.my_app.dto.request.ClassGroupCreateRequest;
import com.example.my_app.dto.response.ClassGroupResponse;
import com.example.my_app.model.ClassGroup;
import org.springframework.stereotype.Component;

@Component
public class ClassGroupMapper {

    public ClassGroupResponse toResponse(ClassGroup classGroup) {
        return ClassGroupResponse.builder()
                .classGroupId(classGroup.getId())
                .name(classGroup.getName())
                .lvl(classGroup.getLvl())
                .teacherId(classGroup.getTeacher().getId())
                .build();
    }

    public ClassGroup toEntity(ClassGroupCreateRequest classGroupCreateRequest) {
        return ClassGroup.builder()
                .name(classGroupCreateRequest.name())
                .lvl(classGroupCreateRequest.lvl())
                .build();
    }
}
