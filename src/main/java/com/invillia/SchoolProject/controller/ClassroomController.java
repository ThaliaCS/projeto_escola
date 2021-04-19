package com.invillia.SchoolProject.controller;

import com.invillia.SchoolProject.dto.request.ClassroomDTO;
import com.invillia.SchoolProject.model.Classroom;
import com.invillia.SchoolProject.service.ClassroomService;
import com.invillia.SchoolProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/classrooms")
@CrossOrigin(origins = "http://localhost:3000")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private StudentService studentService;

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity save(@RequestBody @Valid ClassroomDTO classroomDTO) {

        classroomService.save(classroomDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //GET
    @GetMapping
    public ResponseEntity <List<Classroom>> listAll() {

        return ResponseEntity.ok(classroomService.listAll());

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {

        return ResponseEntity.of(classroomService.findById(id));
    }


    //PUT
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ClassroomDTO classroomDTO) {

      return ResponseEntity.ok(classroomService.update(id, classroomDTO));
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        classroomService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


//    //ADICIONA ALUNOS NA TURMA
//    @PutMapping("/{classroomId}/students/{studentId}")
//    public ResponseEntity<ClassroomDTO> enrollStudentToClassroom(@PathVariable Long classroomId, @PathVariable Long studentId){
//
//        //Retornar erro quando o aluno já estiver adicionado
//        Classroom classroom = classroomService.findById(classroomId).get();
//        Student student = studentService.findById(studentId).get();
//        classroom.enrollStudent(student);
//
//        return ResponseEntity.ok(classroomService.save(classroom));
//    }

}



