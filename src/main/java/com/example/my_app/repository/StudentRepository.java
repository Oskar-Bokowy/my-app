package com.example.my_app.repository;

import com.example.my_app.dto.response.StudentResponse;
import com.example.my_app.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<StudentResponse> findByClassGroup_Id(Long classGroupId, Pageable pageable);

    Set<Student> findByIdInAndClassGroup_Id( Set<Long> studentsIds,Long classGroupId);

    @Query("""
               SELECT new com.example.my_app.dto.response.StudentResponse(
               s.id,
               s.name,
               s.surname,
               s.email,
               s.phoneNr,
               s.dateOfBirth
               )
               From Student s
            """)
    Page<StudentResponse> findAllStudent(Pageable pageable);
}
