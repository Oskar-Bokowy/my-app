package com.example.my_app;


import com.example.my_app.dto.request.ClassGroupRequest;
import com.example.my_app.dto.response.ClassGroupResponse;
import com.example.my_app.exception.exception.ClassGroupNotFoundExceptionException;
import com.example.my_app.mapper.ClassGroupMapper;
import com.example.my_app.model.ClassGroup;
import com.example.my_app.model.Student;
import com.example.my_app.model.Teacher;
import com.example.my_app.repository.ClassGroupRepository;
import com.example.my_app.repository.StudentRepository;
import com.example.my_app.service.ClassGroupService;
import com.example.my_app.service.StudentService;
import com.example.my_app.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClassGroupServiceTest {

    @Mock
    private StudentService studentService;
    @Mock
    private TeacherService teacherService;
    @Mock
    private ClassGroupRepository classGroupRepository;
    @Mock
    private ClassGroupMapper classGroupMapper;
    @InjectMocks
    private ClassGroupService classGroupService;

    @Test
    void shouldCreateClassGroup() {
        //given
        Teacher teacher = TestFactoryTeacher.createTestTeacher();
        teacher.setId(1L);
        ClassGroup classGroup = TestFactoryClassGroup.createTestClassGroup();
        ClassGroupResponse response = TestFactoryClassGroup.createTestClassGroupResponse();
        ClassGroupRequest request = TestFactoryClassGroup.createTestClassGroupRequest(1L);


        when(classGroupMapper.toEntity(request)).thenReturn(classGroup);
        when(classGroupRepository.save(classGroup)).thenReturn(classGroup);
        when(classGroupMapper.toResponse(classGroup)).thenReturn(response);

        //when
        ClassGroupResponse classGroupResult = classGroupService.crateClassGroup(request);

        //then
        assertNotNull(classGroupResult);
        assertEquals(classGroup.getName(), classGroupResult.name());
        assertEquals(classGroup.getLvl(), classGroupResult.lvl());
    }


    @Test
    void shouldDeleteClassGroupById() {
        //given
        ClassGroup classGroup = TestFactoryClassGroup.createTestClassGroup();
        classGroup.setId(1L);

        //when
        classGroupService.deleteClassGroupById(1L);

        //then
        verify(classGroupRepository, times(1)).deleteById(1L);
        verifyNoMoreInteractions(classGroupRepository);
    }

    @Test
    void shouldUpdatedClassGroupById() {
        //given
        Teacher teacher = TestFactoryTeacher.createTestTeacher();
        teacher.setId(1L);
        ClassGroup classGroup = TestFactoryClassGroup.createTestClassGroup();
        classGroup.setId(1L);
        ClassGroupResponse response = ClassGroupResponse.builder()
                .name("Jagody")
                .lvl("C1")
                .build();

        ClassGroupRequest request = ClassGroupRequest.builder()
                .name("Jagody")
                .lvl("C1")
                .teacherId(1L)
                .build();

        when(classGroupRepository.findById(1L)).thenReturn(Optional.of(classGroup));
        when(classGroupMapper.toResponse(classGroup)).thenReturn(response);

        //when
        ClassGroupResponse classGroupResult = classGroupService.updatedClassGroupById(request, 1L);

        //then
        assertNotNull(classGroupResult);
        assertEquals(classGroup.getName(), classGroupResult.name());
        assertEquals(classGroup.getLvl(), classGroupResult.lvl());
    }

    @Test
    void ShouldReturnClassGroupById() {
        //given
        Teacher teacher = TestFactoryTeacher.createTestTeacher();
        teacher.setId(1L);
        ClassGroup classGroup = TestFactoryClassGroup.createTestClassGroup();
        ClassGroupResponse response = TestFactoryClassGroup.createTestClassGroupResponse();

        when(classGroupRepository.findById(1L)).thenReturn(Optional.of(classGroup));
        when(classGroupMapper.toResponse(classGroup)).thenReturn(response);

        //when
        ClassGroupResponse classGroupResult = classGroupService.findClassGroupById(1L);

        //then
        assertNotNull(classGroupResult);
        assertEquals(classGroup.getName(), classGroupResult.name());
        assertEquals(classGroup.getLvl(), classGroupResult.lvl());
    }

    @Test
    void shouldThrowClassGroupNotFoundException() {
        //given & when & then
        assertThrows(ClassGroupNotFoundExceptionException.class,
                () -> classGroupService.findClassGroupById(1L));
    }

    @Test
    void shouldAddStudentToClassGroup() {
        // given
        Student student = TestFactoryStudent.createTestStudent();
        student.setId(1L);
        ClassGroup classGroup = TestFactoryClassGroup.createTestClassGroup();

        when(studentService.getStudentById(1L)).thenReturn(student);
        when(classGroupRepository.findById(1L)).thenReturn(Optional.of(classGroup));

        // when
        classGroupService.addStudentToClassGroup(1L, 1L);

        // then
        assertThat(student.getClassGroup()).isEqualTo(classGroup);

        assertThat(classGroup.getStudents()).contains(student);
    }

    @Test
    void shouldAddStudentsToClassGroup() {
        // given
        Student student1 = TestFactoryStudent.createTestStudent();
        student1.setId(1L);
        Student student2 = TestFactoryStudent.createTestStudent();
        student2.setId(2L);
        ClassGroup classGroup = TestFactoryClassGroup.createTestClassGroup();

        when(studentService.getStudentById(1L)).thenReturn(student1);
        when(studentService.getStudentById(2L)).thenReturn(student2);
        when(classGroupRepository.findById(1L)).thenReturn(Optional.of(classGroup));

        Set<Long> studentIds = Set.of(student1.getId(), student2.getId());
        // when
        classGroupService.addStudentsToClassGroup(1L, studentIds);

        // then
        assertThat(student1.getClassGroup()).isEqualTo(classGroup);
        assertThat(student2.getClassGroup()).isEqualTo(classGroup);

        assertThat(classGroup.getStudents()).contains(student1);
        assertThat(classGroup.getStudents()).contains(student2);
    }


    @Test
    void shouldRemoveStudentFromClassGroup() {
        // given
        Student student = TestFactoryStudent.createTestStudent();
        student.setId(1L);
        ClassGroup classGroup = TestFactoryClassGroup.createTestClassGroup();

        when(studentService.getStudentById(1L)).thenReturn(student);
        when(classGroupRepository.findById(1L)).thenReturn(Optional.of(classGroup));

        // when
        classGroupService.removeStudentToClassGroup(1L, 1L);

        // then
        assertThat(student.getClassGroup()).isNull();

        assertThat(classGroup.getStudents()).isEmpty();
    }

    @Test
    void shouldRemoveStudentsFromClassGroup() {
        // given
        Student student1 = TestFactoryStudent.createTestStudent();
        student1.setId(1L);
        Student student2 = TestFactoryStudent.createTestStudent();
        student2.setId(2L);
        ClassGroup classGroup = TestFactoryClassGroup.createTestClassGroup();

        when(studentService.getStudentById(1L)).thenReturn(student1);
        when(studentService.getStudentById(2L)).thenReturn(student2);
        when(classGroupRepository.findById(1L)).thenReturn(Optional.of(classGroup));

        Set<Long> studentIds = Set.of(student1.getId(), student2.getId());
        // when
        classGroupService.removeStudentsToClassGroup(1L, studentIds);

        // then
        assertThat(student1.getClassGroup()).isNull();
        assertThat(student2.getClassGroup()).isNull();

        assertThat(classGroup.getStudents()).isEmpty();
    }

    @Test
    void shouldChangeTeacherInClassGroup() {
        // given
        Teacher teacher1 = TestFactoryTeacher.createTestTeacher();
        teacher1.setId(1L);
        Teacher teacher2 = TestFactoryTeacher.createTestTeacher();
        teacher2.setId(2L);
        ClassGroup classGroup = TestFactoryClassGroup.createTestClassGroup();
        classGroup.setTeacher(teacher1);

        when(teacherService.getTeacherById(2L)).thenReturn(teacher2);
        when(classGroupRepository.findById(1L)).thenReturn(Optional.of(classGroup));

        // when
        classGroupService.changeTeacherInClassGroup(1L, 2L);

        // then
        assertThat(classGroup.getTeacher()).isEqualTo(teacher2);
    }


}
