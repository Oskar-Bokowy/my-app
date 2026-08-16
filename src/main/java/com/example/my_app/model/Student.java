package com.example.my_app.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Getter
@Setter
@Table(name = "student")
@AttributeOverride(
        name = "id",
        column = @Column(name = "student_id")
)
public class Student extends Person {
    private LocalDate dateOfBirth;

    private String loginToApp;
    private String passwordToApp;

    @ManyToOne
    @JoinColumn(name = "class_group_id")
    public ClassGroup classGroup;


}
