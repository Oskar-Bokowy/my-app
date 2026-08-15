package com.example.my_app.service;

import com.example.my_app.dto.request.ClassGroupCreateRequest;
import com.example.my_app.dto.response.ClassGroupResponse;
import com.example.my_app.exception.exception.ClassGroupNotFoundExceptionException;
import com.example.my_app.mapper.ClassGroupMapper;
import com.example.my_app.model.ClassGroup;
import com.example.my_app.repository.ClassGroupRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClassGroupService {
    private ClassGroupRepository classGroupRepository;
    private ClassGroupMapper classGroupMapper;

    public ClassGroupResponse crateClassGroup(ClassGroupCreateRequest classGroupCreateRequest) {
        ClassGroup classGroup = classGroupMapper.toEntity(classGroupCreateRequest);
        ClassGroup savedClassGroup = classGroupRepository.save(classGroup);
        return classGroupMapper.toResponse(savedClassGroup);
    }

    public ClassGroupResponse findClassGroupById(Long id) {
        ClassGroup classGroup = classGroupRepository.findById(id)
                .orElseThrow(() -> new ClassGroupNotFoundExceptionException("Class Group not found", HttpStatus.NOT_FOUND));
        return classGroupMapper.toResponse(classGroup);
    }

    public void deleteClassGroupById(Long id) {
        classGroupRepository.deleteById(id);
    }

    @Transactional
    public ClassGroupResponse updatedClassGroupById(ClassGroupCreateRequest updatedClassGroup, Long id) {
        ClassGroup existing = classGroupRepository.findById(id)
                .orElseThrow(() -> new ClassGroupNotFoundExceptionException("Class Group not found", HttpStatus.NOT_FOUND));
        existing.setName(updatedClassGroup.name());
        existing.setLvl(updatedClassGroup.lvl());
        return classGroupMapper.toResponse(existing);
    }
}
