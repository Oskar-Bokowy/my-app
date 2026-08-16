package com.example.my_app.controller;

import com.example.my_app.dto.request.LessonRequest;
import com.example.my_app.dto.response.LessonResponse;
import com.example.my_app.service.LessonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/lessons")
public class LessonController {
    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity<LessonResponse> createLesson(@RequestBody @Valid LessonRequest lessonRequest) {
        LessonResponse response = lessonService.createLesson(lessonRequest);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonResponse> getLessonById(@PathVariable Long lessonId) {
        LessonResponse response = lessonService.getLessonById(lessonId);
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("updated/{lessonId}")
    public ResponseEntity<LessonResponse> updatedLessonById(@RequestBody @Valid LessonRequest lessonRequest, @PathVariable Long lessonId) {
        LessonResponse response = lessonService.updatedById(lessonRequest, lessonId);
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("{lessonId}")
    public ResponseEntity<Void> deleteLessonById(@PathVariable Long lessonId) {
        lessonService.deleteLessonById(lessonId);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/markAttendance/{lessonId}")
    public ResponseEntity<Void> markAttendance(@PathVariable Long lessonId, @RequestBody Set<Long> studentIds)  {
        lessonService.markAttendance(lessonId, studentIds);
        return ResponseEntity.status(204).build();
    }
}
