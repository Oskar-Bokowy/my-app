package com.example.my_app.dto.response;

import lombok.Builder;

@Builder
public record ClassGroupResponse  (
        Long  classGroupId,
        String name,
        String lvl,
        Long teacherId
){
}
