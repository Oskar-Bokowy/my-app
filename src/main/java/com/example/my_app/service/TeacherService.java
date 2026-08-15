package com.example.my_app.service;

import com.example.my_app.dto.request.TeacherRequest;
import com.example.my_app.dto.response.TeacherResponse;
import com.example.my_app.exception.exception.TeacherNotFoundException;
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
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;


    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = teacherMapper.toEntity(teacherRequest);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return teacherMapper.toResponse(savedTeacher);
    }

    public TeacherResponse findTeacherById(Long id) {
        Teacher response = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found", HttpStatus.NOT_FOUND));
        return teacherMapper.toResponse(response);
    }

    protected Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found", HttpStatus.NOT_FOUND));
    }

    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Transactional
    public TeacherResponse updatedTeacherById(TeacherRequest updatedTeacher, Long id) {
        Teacher existing = getTeacherById(id);
        existing.setName(updatedTeacher.name());
        existing.setSurname(updatedTeacher.surname());
        existing.setEmail(updatedTeacher.email());
        existing.setPhoneNr(updatedTeacher.email());
        return teacherMapper.toResponse(existing);
    }
}
