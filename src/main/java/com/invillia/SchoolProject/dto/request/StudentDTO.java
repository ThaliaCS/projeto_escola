package com.invillia.SchoolProject.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.invillia.SchoolProject.model.Classroom;

public class StudentDTO {

    private Long id;

    private String name;

    private String lastname;

    private Classroom classroom;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Long classroom_id;

    public StudentDTO() {}


    public StudentDTO(Long id, String name, String lastname, Classroom classroom, Long classroom_id) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.classroom = classroom;
        this.classroom_id = classroom_id;
    }

    public StudentDTO(Long id, String name, String lastname, Long classroom_id) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.classroom_id = classroom_id;
    }

    public StudentDTO(long l, String s) {
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

    public Long getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(Long classroom_id) {
        this.classroom_id = classroom_id;
    }
}

