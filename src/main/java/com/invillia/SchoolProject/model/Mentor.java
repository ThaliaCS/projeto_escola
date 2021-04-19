package com.invillia.SchoolProject.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The mentor name cannot be empty")
     private String name;

    @NotEmpty(message = "The mentor lastname cannot be empty")
    private String lastname;

    public Mentor(Long id, @NotEmpty(message = "The mentor name cannot be empty") String name, @NotEmpty(message = "The mentor lastname cannot be empty") String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    public Mentor() {
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
