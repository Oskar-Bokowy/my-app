package com.example.my_app.service;

import com.example.my_app.dto.request.LessonRequest;
import com.example.my_app.dto.response.LessonResponse;
import com.example.my_app.exception.exception.LessonNotFoundException;
import com.example.my_app.mapper.LessonMapper;
import com.example.my_app.model.ClassGroup;
import com.example.my_app.model.Lesson;
import com.example.my_app.model.Student;
import com.example.my_app.repository.LessonRepository;
import com.example.my_app.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final ClassGroupService classGroupService;
    private final StudentRepository studentRepository;

    public LessonResponse createLesson(LessonRequest lessonRequest) {
        ClassGroup classGroup = classGroupService.getClassGroupById(lessonRequest.classGroupId());
        Lesson lesson = lessonMapper.toEntity(lessonRequest);
        lesson.setClassGroup(classGroup);
        Lesson savedLesson = lessonRepository.save(lesson);
        return lessonMapper.toResponse(savedLesson);
    }

    public void deleteLessonById(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }

    public LessonResponse getLessonById(Long lessonId) {
        Lesson response = findLessonById(lessonId);
        return lessonMapper.toResponse(response);
    }

    protected Lesson findLessonById(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new LessonNotFoundException("Lesson not found", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public LessonResponse updatedById(LessonRequest updatedLesson, Long lessonId) {
        Lesson existing = findLessonById(lessonId);
        existing.setLessonDuration(updatedLesson.duration());
        existing.setLessonTopic(updatedLesson.lessonTopic());
        existing.setStart(updatedLesson.start());
        return lessonMapper.toResponse(existing);
    }

    public void markAttendance(Long lessonId, Set<Long> studentsIds)  {
        Lesson lesson = findLessonById(lessonId);
        Long classGroupId = lesson.getClassGroup().getId();
        Set<Student> students =  studentRepository.findByIdInAndClassGroup_Id(studentsIds,classGroupId);
        if (students.size() != studentsIds.size()) {
            throw new IllegalArgumentException("Student not allowed");
        }
        lesson.getPresentStudents().addAll(students);
        lessonRepository.save(lesson);
    }

}
