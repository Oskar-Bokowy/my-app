package com.example.my_app.controller;

import com.example.my_app.dto.request.TeacherRequest;
import com.example.my_app.dto.response.TeacherResponse;
import com.example.my_app.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody @Valid TeacherRequest teacherRequest) {
        TeacherResponse response = teacherService.createTeacher(teacherRequest);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("{teacherId}")
    public ResponseEntity<TeacherResponse> findTeacherById(@PathVariable Long teacherId) {
        TeacherResponse response = teacherService.findTeacherById(teacherId);
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("{teacherId}")
    public ResponseEntity<Void> deleteTeacherById(@PathVariable Long teacherId) {
        teacherService.deleteTeacherById(teacherId);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/updatedTeacher/{teacherId}")
    public ResponseEntity<TeacherResponse> updatedTeacherById(@RequestBody @Valid TeacherRequest updatedTeacher, @PathVariable Long teacherId) {
        TeacherResponse response = teacherService.updatedTeacherById(updatedTeacher, teacherId);
        return ResponseEntity.status(200).body(response);
    }

}
