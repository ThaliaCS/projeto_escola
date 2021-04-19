package com.invillia.SchoolProject.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The  classroom name cannot be empty")
    @Column(unique=true)
    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy="classroom")
    private List<Student> enrolledStudents;

    public Classroom(Long id, @NotEmpty(message = "The  classroom name cannot be empty") String name) {
        this.id = id;
        this.name = name;
    }


    public Classroom() {}
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

    public List<Student> getStudents() {
        return enrolledStudents;
    }

    public void setStudents(List<Student> students) {
        this.enrolledStudents = students;
    }

    public ResponseEntity<Object> enrollStudent(Student student) {
        if (enrolledStudents.contains(student)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            enrolledStudents.add(student);
        }
        return null;
    }
}
