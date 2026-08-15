package com.example.my_app.controller;

import com.example.my_app.dto.request.ClassGroupRequest;
import com.example.my_app.dto.response.ClassGroupResponse;
import com.example.my_app.service.ClassGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/classGroups")
public class ClassGroupController {
    private final ClassGroupService classGroupService;

    @PostMapping
    public ResponseEntity<ClassGroupResponse> createClassGroup(@RequestBody @Valid ClassGroupRequest classGroupRequest) {
        ClassGroupResponse response = classGroupService.crateClassGroup(classGroupRequest);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{classGroupId}")
    public ResponseEntity<ClassGroupResponse> findClassGroupById(@PathVariable Long classGroupId) {
        ClassGroupResponse response = classGroupService.findClassGroupById(classGroupId);
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{classGroupId}")
    public ResponseEntity<Void> deleteClassGroupById(@PathVariable Long classGroupId) {
        classGroupService.deleteClassGroupById(classGroupId);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/updatedClassGroup/{classGroupId}")
    public ResponseEntity<ClassGroupResponse> updatedClassGroupById(@RequestBody @Valid ClassGroupRequest updatedClassGroup, @PathVariable Long classGroupId) {
        ClassGroupResponse response = classGroupService.updatedClassGroupById(updatedClassGroup, classGroupId);
        return ResponseEntity.status(200).body(response);
    }


    @PatchMapping("/{classGroupId}/addStudent/{studentId}")
    public ResponseEntity<Void> addStudentToClassGroup(@PathVariable Long classGroupId, @PathVariable Long studentId) {
        classGroupService.addStudentToClassGroup(classGroupId, studentId);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/{classGroupId}/addStudents")
    public ResponseEntity<Void> addStudentsToClassGroup(@PathVariable Long classGroupId, @RequestBody Set<Long> studentIds) {
        classGroupService.addStudentsToClassGroup(classGroupId, studentIds);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/{classGroupId}/removeStudent/{studentId}")
    public ResponseEntity<Void> removeStudentToClassGroup(@PathVariable Long classGroupId, @PathVariable Long studentId) {
        classGroupService.removeStudentToClassGroup(classGroupId, studentId);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/{classGroupId}/removeStudents")
    public ResponseEntity<Void> removeStudentsToClassGroup(@PathVariable Long classGroupId, @RequestBody Set<Long> studentIds) {
        classGroupService.removeStudentsToClassGroup(classGroupId, studentIds);
        return ResponseEntity.status(204).build();
    }


    @PatchMapping("/{classGroupId}/changeTeacher/{teacherId}")
    public ResponseEntity<Void> changeTeacherToClassGroup(@PathVariable Long classGroupId, @PathVariable Long teacherId) {
        classGroupService.changeTeacherInClassGroup(classGroupId, teacherId);
        return ResponseEntity.status(204).build();
    }

}
