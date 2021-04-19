package com.invillia.SchoolProject.dto.request;

import com.invillia.SchoolProject.model.Student;

import java.util.List;

public class ClassroomDTO {

    private Long id;

    private String name;

    private List<Student> students;

    public ClassroomDTO() {
    }

    public ClassroomDTO(Long id, String name, List<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public ClassroomDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "ClassroomDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
