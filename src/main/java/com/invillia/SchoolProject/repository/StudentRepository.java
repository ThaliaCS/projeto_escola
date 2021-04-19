package com.invillia.SchoolProject.repository;

import com.invillia.SchoolProject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

 //
   //  List<Student> findAllWithNameOnly();

}
