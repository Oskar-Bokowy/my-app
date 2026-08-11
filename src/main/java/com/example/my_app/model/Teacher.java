package com.example.my_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Teacher extends Person {

    @OneToMany(mappedBy = "teacher")
    private Set<ClassGroup> classGroups;

}
