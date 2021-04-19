package com.invillia.SchoolProject.service;

import com.invillia.SchoolProject.dto.request.StudentDTO;
import com.invillia.SchoolProject.mapper.StudentMapper;
import com.invillia.SchoolProject.model.Student;
import com.invillia.SchoolProject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassroomService classroomService;

    private final StudentMapper studentMapper = StudentMapper.INSTANCE;

    public Student save(StudentDTO studentDTO) {

        studentDTO.setClassroom(classroomService.findOne(studentDTO.getClassroom_id()));
        Student studentToSave = studentMapper.toModel(studentDTO);
        Student savedStudent = studentRepository.save(studentToSave);
        return  studentRepository.save(savedStudent);
    }

    public List<Student> listAll() {

        List<Student> allStudents = studentRepository.findAll();

        return allStudents;
    }

    public Optional<Student> findById(Long id) {

        return studentRepository.findById(id);
    }

    public void save(Student record) {

        Student savedStudent = studentRepository.save(record);
    }

    public void deleteById(Long id) {

        studentRepository.deleteById(id);
    }

    public Object update(Long id, StudentDTO studentDTO) {

        return studentRepository.findById(id).
                map(student -> {
                    student.setName(studentDTO.getName());
                    student.setLastname(studentDTO.getLastname());
                    Student updated = studentRepository.save(student);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public Student findOne(Long id) {

        return studentRepository.findById(id).orElse(null);
    }

}


