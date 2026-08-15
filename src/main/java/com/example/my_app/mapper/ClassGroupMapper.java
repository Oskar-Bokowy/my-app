package com.example.my_app.mapper;

import com.example.my_app.dto.request.ClassGroupRequest;
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

    public ClassGroup toEntity(ClassGroupRequest classGroupRequest) {
        return ClassGroup.builder()
                .name(classGroupRequest.name())
                .lvl(classGroupRequest.lvl())
                .build();
    }
}
