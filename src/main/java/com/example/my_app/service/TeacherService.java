package com.example.my_app.service;

import com.example.my_app.dto.request.TeacherCreateRequest;
import com.example.my_app.dto.response.TeacherResponse;
import com.example.my_app.exception.exception.StudentNotFoundException;
import com.example.my_app.mapper.TeacherMapper;
import com.example.my_app.model.Teacher;
import com.example.my_app.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherService {
    private TeacherRepository teacherRepository;
    private TeacherMapper teacherMapper;


    public TeacherResponse createTeacher(TeacherCreateRequest teacherCreateRequest) {
        Teacher teacher = teacherMapper.toEntity(teacherCreateRequest);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return teacherMapper.toResponse(savedTeacher);
    }

    public TeacherResponse findTeacherById(Long id) {
        Teacher response = teacherRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found", HttpStatus.NOT_FOUND));
        return teacherMapper.toResponse(response);
    }

    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Transactional
    public TeacherResponse updatedTeacherById(TeacherCreateRequest updatedTeacher, Long id) {
        Teacher existing = teacherRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found",HttpStatus.NOT_FOUND));
        existing.setName(updatedTeacher.name());
        existing.setSurname(updatedTeacher.surname());
        existing.setEmail(updatedTeacher.email());
        existing.setPhoneNr(updatedTeacher.email());
        return teacherMapper.toResponse(existing);
    }
}
