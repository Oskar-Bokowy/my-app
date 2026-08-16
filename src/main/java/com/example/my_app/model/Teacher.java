package com.example.my_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Table(name = "teacher")
@AttributeOverride(
        name = "id",
        column = @Column(name = "teacher_id")
)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Teacher extends Person {

    @OneToMany(mappedBy = "teacher")
    private Set<ClassGroup> classGroups;

}
