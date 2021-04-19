package com.invillia.SchoolProject.repository;


import com.invillia.SchoolProject.model.Mentor;
import com.invillia.SchoolProject.model.Mentoring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoringRepository extends JpaRepository<Mentoring, Long> {

    boolean existsById(Long student_id);

    Mentor findMentorById(Long id);

}
