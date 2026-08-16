package com.example.my_app;

import com.example.my_app.dto.request.StudentRequest;
import com.example.my_app.dto.response.StudentResponse;
import com.example.my_app.mapper.StudentMapper;
import com.example.my_app.model.Student;
import com.example.my_app.repository.StudentRepository;
import com.example.my_app.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldCreateStudent() {
        //given
        Student student = TestFactoryStudent.createTestStudent();
        StudentResponse response = TestFactoryStudent.createTestStudentResponse();
        StudentRequest request = TestFactoryStudent.createTestStudentRequest();

        when(studentMapper.toEntity(request)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toResponse(student)).thenReturn(response);

        //when
        StudentResponse studentResult = studentService.createStudent(request);

        //then
        assertNotNull(studentResult);
        assertEquals(student.getName(), studentResult.name());
        assertEquals(student.getSurname(), studentResult.surname());
        assertEquals(student.getEmail(), studentResult.email());
        assertEquals(student.getPhoneNr(), studentResult.phoneNr());
        assertEquals(student.getDateOfBirth(), studentResult.dateOfBirth());
    }


    @Test
    void shouldReturnStudentById() {
        //given
        Student student = TestFactoryStudent.createTestStudent();
        student.setId(1L);

        StudentResponse response = TestFactoryStudent.createTestStudentResponse();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toResponse(student)).thenReturn(response);

        //when
        StudentResponse studentResult = studentService.findStudentById(1L);

        //then
        assertNotNull(studentResult);
        assertEquals(student.getName(), studentResult.name());
        assertEquals(student.getSurname(), studentResult.surname());
        assertEquals(student.getEmail(), studentResult.email());
        assertEquals(student.getPhoneNr(), studentResult.phoneNr());
        assertEquals(student.getDateOfBirth(), studentResult.dateOfBirth());
    }


    @Test
    void shouldDeleteStudent(){
        //given
        Student student = TestFactoryStudent.createTestStudent();
        student.setId(1L);

        //when
        studentService.deleteStudentById(1L);

        //then
        verify(studentRepository,times(1)).deleteById(1L);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    void shouldUpdatedStudentById(){
        //given
        Student student = TestFactoryStudent.createTestStudent();
        student.setId(1L);
        StudentResponse response = StudentResponse.builder()
                .name("Daniel")
                .surname("Jak")
                .email("JaO2@gmai.com")
                .phoneNr("656858989")
                .dateOfBirth(LocalDate.parse("2012-05-15"))
                .build();
        StudentRequest request = StudentRequest.builder()
                .name("Daniel")
                .surname("Jak")
                .email("JaO2@gmai.com")
                .phoneNr("656858989")
                .dateOfBirth(LocalDate.parse("2012-05-15"))
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toResponse(student)).thenReturn(response);

        //when
        StudentResponse studentResult = studentService.updateStudentById(request,1L);

        //then
        assertNotNull(studentResult);
        assertEquals(student.getName(), studentResult.name());
        assertEquals(student.getSurname(), studentResult.surname());
        assertEquals(student.getEmail(), studentResult.email());
        assertEquals(student.getPhoneNr(), studentResult.phoneNr());
        assertEquals(student.getDateOfBirth(), studentResult.dateOfBirth());
    }
}
