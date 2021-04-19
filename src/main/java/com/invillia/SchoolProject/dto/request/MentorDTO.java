package com.invillia.SchoolProject.dto.request;

import com.invillia.SchoolProject.model.Mentoring;

import java.util.List;

public class MentorDTO {

    private Long id;

    private String name;

    private String lastname;

    private List<Mentoring> mentorings;

    public MentorDTO() {
    }

    public MentorDTO(Long id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    public MentorDTO(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
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
}
