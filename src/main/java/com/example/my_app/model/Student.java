package com.example.my_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
public class Student extends Person {
    private LocalDate dateOfBirth;

    private String loginToApp;
    private String passwordToApp;

    @ManyToOne
    @JoinColumn(name = "class_group_id")
    public ClassGroup classGroup;


}
