package com.invillia.SchoolProject.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The student name cannot be empty")
    @Size(max = 65)
    private String name;

    @NotEmpty(message = "The student last name cannot be empty")
    @Size(max = 65)
    private String lastname;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Classroom classroom;

    public Student(Long id, String name, String lastname, Classroom classroom) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.classroom = classroom;
    }



    public Student() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

}
