package com.example.my_app.service;

import com.example.my_app.dto.request.ClassGroupRequest;
import com.example.my_app.dto.response.ClassGroupResponse;
import com.example.my_app.dto.response.StudentResponse;
import com.example.my_app.exception.exception.ClassGroupNotFoundExceptionException;
import com.example.my_app.mapper.ClassGroupMapper;
import com.example.my_app.model.ClassGroup;
import com.example.my_app.model.Student;
import com.example.my_app.model.Teacher;
import com.example.my_app.repository.ClassGroupRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class ClassGroupService {
    private final ClassGroupRepository classGroupRepository;
    private final ClassGroupMapper classGroupMapper;
    private final TeacherService teacherService;
    private final StudentService studentService;


    @Transactional
    public void removeStudentToClassGroup(Long classGroupId, Long studentId) {
        ClassGroup classGroup = getClassGroupById(classGroupId);
        Student student = studentService.getStudentById(studentId);

        classGroup.getStudents().remove(student);
        student.setClassGroup(null);
    }

    @Transactional
    public void removeStudentsToClassGroup(Long classGroupId, Set<Long> studentIds) {
        studentIds.forEach(studentId -> removeStudentToClassGroup(classGroupId, studentId));
    }

    @Transactional
    public void addStudentToClassGroup(Long classGroupId, Long studentId) {
        ClassGroup classGroup = getClassGroupById(classGroupId);
        Student student = studentService.getStudentById(studentId);
        if (classGroup.getStudents().size() >= 8) {
            log.warn("ClassGroup {} already has 8 students", classGroupId);
        }
        classGroup.getStudents().add(student);
        student.setClassGroup(classGroup);
    }


    @Transactional
    public void addStudentsToClassGroup(Long classGroupId, Set<Long> studentIds) {
        studentIds.forEach(studentId -> addStudentToClassGroup(classGroupId, studentId));
    }


    @Transactional
    public void changeTeacherInClassGroup(Long classGroupId, Long teacherId) {
        ClassGroup classGroup = getClassGroupById(classGroupId);
        Teacher newTeacher = teacherService.getTeacherById(teacherId);
        classGroup.setTeacher(newTeacher);
    }

    public ClassGroupResponse crateClassGroup(ClassGroupRequest classGroupRequest) {
        Teacher teacher = teacherService.getTeacherById(classGroupRequest.teacherId());
        ClassGroup classGroup = classGroupMapper.toEntity(classGroupRequest);
        classGroup.setTeacher(teacher);
        ClassGroup savedClassGroup = classGroupRepository.save(classGroup);
        return classGroupMapper.toResponse(savedClassGroup);
    }

    public ClassGroupResponse findClassGroupById(Long id) {
        ClassGroup classGroup = classGroupRepository.findById(id)
                .orElseThrow(() -> new ClassGroupNotFoundExceptionException("Class Group not found", HttpStatus.NOT_FOUND));
        return classGroupMapper.toResponse(classGroup);
    }

    public ClassGroup getClassGroupById(Long id) {
        return classGroupRepository.findById(id)
                .orElseThrow(() -> new ClassGroupNotFoundExceptionException("Class Group not found", HttpStatus.NOT_FOUND));
    }

    public void deleteClassGroupById(Long id) {
        classGroupRepository.deleteById(id);
    }

    @Transactional
    public ClassGroupResponse updatedClassGroupById(ClassGroupRequest updatedClassGroup, Long id) {
        ClassGroup existing = classGroupRepository.findById(id)
                .orElseThrow(() -> new ClassGroupNotFoundExceptionException("Class Group not found", HttpStatus.NOT_FOUND));
        existing.setName(updatedClassGroup.name());
        existing.setLvl(updatedClassGroup.lvl());
        return classGroupMapper.toResponse(existing);
    }


}
