package com.example.my_app.controller;

import com.example.my_app.dto.request.StudentRequest;
import com.example.my_app.dto.response.StudentResponse;
import com.example.my_app.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody @Valid StudentRequest studentRequest) {
        StudentResponse response = studentService.createStudent(studentRequest);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponse> findStudentById(@PathVariable Long studentId) {
        StudentResponse response = studentService.findStudentById(studentId);
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/udpetdStudent/{studentId}")
    public ResponseEntity<StudentResponse> updatedStudentById(@RequestBody @Valid StudentRequest updatedStudent, @PathVariable Long studentId) {
        StudentResponse response = studentService.updateStudentById(updatedStudent, studentId);
        return ResponseEntity.status(200).body(response);
    }
}
