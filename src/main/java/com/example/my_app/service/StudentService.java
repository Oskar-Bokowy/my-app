package com.example.my_app.service;

import com.example.my_app.dto.request.StudentRequest;
import com.example.my_app.dto.response.StudentResponse;
import com.example.my_app.exception.exception.StudentNotFoundException;
import com.example.my_app.mapper.StudentMapper;
import com.example.my_app.model.Student;
import com.example.my_app.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student student = studentMapper.toEntity(studentRequest);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponse(savedStudent);
    }


    public StudentResponse findStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found", HttpStatus.NOT_FOUND));
        return studentMapper.toResponse(student);
    }

    protected Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found", HttpStatus.NOT_FOUND));
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public StudentResponse updateStudentById(StudentRequest updatedStudent, Long id) {
        Student existing = getStudentById(id);
        existing.setName(updatedStudent.name());
        existing.setSurname(updatedStudent.surname());
        existing.setPhoneNr(updatedStudent.phoneNr());
        existing.setEmail(updatedStudent.email());
        existing.setDateOfBirth(updatedStudent.dateOfBirth());
        return studentMapper.toResponse(existing);
    }
}
