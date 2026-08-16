package com.example.my_app;

import com.example.my_app.dto.request.LessonRequest;
import com.example.my_app.dto.response.LessonResponse;
import com.example.my_app.exception.exception.LessonNotFoundException;
import com.example.my_app.exception.exception.StudentNotFoundException;
import com.example.my_app.mapper.LessonMapper;
import com.example.my_app.model.ClassGroup;
import com.example.my_app.model.Lesson;
import com.example.my_app.model.LessonDuration;
import com.example.my_app.model.Student;
import com.example.my_app.repository.LessonRepository;
import com.example.my_app.repository.StudentRepository;
import com.example.my_app.service.ClassGroupService;
import com.example.my_app.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class LessonServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private ClassGroupService classGroupService;
    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private LessonMapper lessonMapper;
    @InjectMocks
    private LessonService lessonService;


    @Test
    void shouldCreateLesson() {
        //given
        ClassGroup classGroup = TestFactoryClassGroup.createTestClassGroup();
        classGroup.setId(1L);
        Lesson lesson = TestFactoryLesson.createTestLesson();
        LessonRequest request = TestFactoryLesson.createTestLessonRequest(1L);
        LessonResponse response = TestFactoryLesson.createTestLessonResponse();

        when(classGroupService.getClassGroupById(1L)).thenReturn(classGroup);
        when(lessonMapper.toEntity(request)).thenReturn(lesson);
        when(lessonRepository.save(lesson)).thenReturn(lesson);
        when(lessonMapper.toResponse(lesson)).thenReturn(response);

        //when
        LessonResponse resultResponse = lessonService.createLesson(request);

        //then
        assertNotNull(resultResponse);
        assertEquals(lesson.getLessonTopic(), resultResponse.lessonTopic());
        assertEquals(lesson.getStart(), resultResponse.start());
        assertEquals(lesson.getLessonDuration(), resultResponse.duration());
    }

    @Test
    void shouldDeleteLessonById() {
        //given
        Lesson lesson = TestFactoryLesson.createTestLesson();
        lesson.setLessonId(1L);

        //when
        lessonService.deleteLessonById(1L);
        //then
        verify(lessonRepository, times(1)).deleteById(1L);
        verifyNoMoreInteractions(lessonRepository);
    }

    @Test
    void shouldUpdatedLessonById() {
        //given
        Lesson lesson = TestFactoryLesson.createTestLesson();
        lesson.setLessonId(1L);
        LessonResponse response = LessonResponse.builder()
                .lessonTopic("Quest 1")
                .duration(LessonDuration.MIN_90)
                .start(LocalDateTime.parse("2025-10-06T14:00:00"))
                .build();
        LessonRequest request = LessonRequest.builder()
                .lessonTopic("Quest 1")
                .duration(LessonDuration.MIN_90)
                .start(LocalDateTime.parse("2025-10-06T14:00:00"))
                .build();

        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));
        when(lessonMapper.toResponse(lesson)).thenReturn(response);

        //when
        LessonResponse resultResponse = lessonService.updatedById(request, 1L);

        //then
        assertNotNull(resultResponse);
        assertEquals(lesson.getLessonTopic(), resultResponse.lessonTopic());
        assertEquals(lesson.getStart(), resultResponse.start());
        assertEquals(lesson.getLessonDuration(), resultResponse.duration());
    }

    @Test
    void shouldReturnLessonById() {
        //given
        Lesson lesson = TestFactoryLesson.createTestLesson();
        lesson.setLessonId(1L);
        LessonResponse response = TestFactoryLesson.createTestLessonResponse();

        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));
        when(lessonMapper.toResponse(lesson)).thenReturn(response);

        //when
        LessonResponse resultResponse = lessonService.getLessonById(1L);

        //then
        assertNotNull(resultResponse);
        assertEquals(lesson.getLessonTopic(), resultResponse.lessonTopic());
        assertEquals(lesson.getStart(), resultResponse.start());
        assertEquals(lesson.getLessonDuration(), resultResponse.duration());
    }

    @Test
    void  markAttendance(){
        //given
        Student student1 = TestFactoryStudent.createTestStudent();
        student1.setId(1L);
        Student student2 = TestFactoryStudent.createTestStudent();
        student2.setId(2L);
        ClassGroup classGroup = TestFactoryClassGroup.createTestClassGroup();
        classGroup.setId(1L);
        classGroup.getStudents().add(student1);
        classGroup.getStudents().add(student2);
        student1.setClassGroup(classGroup);
        student2.setClassGroup(classGroup);
        Lesson lesson = TestFactoryLesson.createTestLesson();
        lesson.setLessonId(1L);
        lesson.setClassGroup(classGroup);


        Set<Long> studentIds = Set.of(student1.getId(), student2.getId());
        List<Student> students = List.of(student1, student2);
        when(studentRepository.findByIdInAndClassGroup_Id(studentIds,lesson.getClassGroup().getId())).thenReturn(new HashSet<>(students));
        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));

        //when
        lessonService.markAttendance(1L,studentIds);

        //then
        assertThat(lesson.getPresentStudents()).containsExactlyInAnyOrder(student1, student2);
    }

    @Test
    void markAttendanceThrowStudentNotFound() {
        // given
        Student student1 = TestFactoryStudent.createTestStudent();
        student1.setId(1L);

        ClassGroup classGroup = TestFactoryClassGroup.createTestClassGroup();
        classGroup.setId(1L);

        Lesson lesson = TestFactoryLesson.createTestLesson();
        lesson.setLessonId(1L);
        lesson.setClassGroup(classGroup);

        Set<Long> studentIds = Set.of(1L, 2L);
        List<Student> students = List.of(student1);

        when(studentRepository.findByIdInAndClassGroup_Id(studentIds, lesson.getClassGroup().getId())).thenReturn(new HashSet<>(students));
        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));

        // when & then
        assertThrows(IllegalArgumentException.class,
                () -> lessonService.markAttendance(1L, studentIds)
        );
    }

    @Test
    void shouldThrowLessonNotFound(){
        //given & when & then
        assertThrows(LessonNotFoundException.class,
                ()->lessonService.getLessonById(1L));
    }
}
