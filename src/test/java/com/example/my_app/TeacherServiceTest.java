package com.example.my_app;

import com.example.my_app.dto.request.StudentRequest;
import com.example.my_app.dto.request.TeacherRequest;
import com.example.my_app.dto.response.StudentResponse;
import com.example.my_app.dto.response.TeacherResponse;
import com.example.my_app.exception.exception.TeacherNotFoundException;
import com.example.my_app.mapper.TeacherMapper;
import com.example.my_app.model.Student;
import com.example.my_app.model.Teacher;
import com.example.my_app.repository.TeacherRepository;
import com.example.my_app.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private TeacherMapper teacherMapper;
    @InjectMocks
    private TeacherService teacherService;

    @Test
    void shouldCreateTeacher() {
        //given
        Teacher teacher = TestFactoryTeacher.createTestTeacher();
        TeacherResponse response = TestFactoryTeacher.createTestTeacherResponse();
        TeacherRequest request = TestFactoryTeacher.createTestTeacherRequest();

        when(teacherMapper.toEntity(request)).thenReturn(teacher);
        when(teacherRepository.save(teacher)).thenReturn(teacher);
        when((teacherMapper.toResponse(teacher))).thenReturn(response);

        //when
        TeacherResponse teacherResult = teacherService.createTeacher(request);

        //then
        assertNotNull(teacherResult);
        assertEquals(teacher.getName(), teacherResult.name());
        assertEquals(teacher.getSurname(), teacherResult.surname());
        assertEquals(teacher.getEmail(), teacherResult.email());
        assertEquals(teacher.getPhoneNr(), teacherResult.phoneNr());
    }


    @Test
    void shouldReturnTeacherById() {
        //given
        Teacher teacher = TestFactoryTeacher.createTestTeacher();
        teacher.setId(1L);
        TeacherResponse response = TestFactoryTeacher.createTestTeacherResponse();

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(teacherMapper.toResponse(teacher)).thenReturn(response);

        //when
        TeacherResponse teacherResult = teacherService.findTeacherById(1L);

        //then
        assertNotNull(teacherResult);
        assertEquals(teacher.getName(), teacherResult.name());
        assertEquals(teacher.getSurname(), teacherResult.surname());
        assertEquals(teacher.getEmail(), teacherResult.email());
        assertEquals(teacher.getPhoneNr(), teacherResult.phoneNr());
    }

    @Test
    void shouldDeleteStudentById() {
        //given
        Teacher teacher = TestFactoryTeacher.createTestTeacher();
        teacher.setId(1L);

        //when
        teacherService.deleteTeacherById(1L);

        //then
        verify(teacherRepository, times(1)).deleteById(1L);
        verifyNoMoreInteractions(teacherRepository);
    }

    @Test
    void shouldUpdatedTeacherById() {
        //given
        Teacher teacher = TestFactoryTeacher.createTestTeacher();
        teacher.setId(1L);
        TeacherResponse response = TeacherResponse.builder()
                .name("Daniel")
                .surname("Jak")
                .email("JaO2@gmai.com")
                .phoneNr("656858989")
                .build();
        TeacherRequest request = TeacherRequest.builder()
                .name("Daniel")
                .surname("Jak")
                .email("JaO2@gmai.com")
                .phoneNr("656858989")
                .build();

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(teacherMapper.toResponse(teacher)).thenReturn(response);

        //when
        TeacherResponse teacherResult = teacherService.updatedTeacherById(request, 1L);

        //then
        assertNotNull(teacherResult);
        assertEquals(teacher.getName(), teacherResult.name());
        assertEquals(teacher.getSurname(), teacherResult.surname());
        assertEquals(teacher.getEmail(), teacherResult.email());
        assertEquals(teacher.getPhoneNr(), teacherResult.phoneNr());
    }

    @Test
    void shouldThrowTeacherNotFound() {
        //when & then & given
        assertThrows(TeacherNotFoundException.class,
                () -> teacherService.findTeacherById(1L));
    }
}
