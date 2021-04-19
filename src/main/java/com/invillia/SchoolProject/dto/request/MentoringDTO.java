package com.invillia.SchoolProject.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.invillia.SchoolProject.model.Mentor;
import com.invillia.SchoolProject.model.Student;

public class MentoringDTO {

    private Long id;

    private Mentor mentor;

    private Student student;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long mentor_id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long student_id;

    public MentoringDTO() {}

    public MentoringDTO(Long id, Mentor mentor, Student student, Long mentor_id, Long student_id) {
        this.id = id;
        this.mentor = mentor;
        this.student = student;
        this.mentor_id = mentor_id;
        this.student_id = student_id;
    }

    public MentoringDTO(Long id, Long mentor_id, Long student_id) {
        this.id = id;
        this.mentor_id = mentor_id;
        this.student_id = student_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMentor_id() {
        return mentor_id;
    }

    public void setMentor_id(Long mentor_id) {
        this.mentor_id = mentor_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

