package com.invillia.SchoolProject.controller;

import com.invillia.SchoolProject.dto.request.StudentDTO;
import com.invillia.SchoolProject.model.Student;
import com.invillia.SchoolProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity <List<StudentDTO>> save(@RequestBody @Valid  StudentDTO studentDTO) {

        if( studentService.save(studentDTO) != null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //GET
    @GetMapping
    public ResponseEntity<List<Student>> listAll() {

        return ResponseEntity.ok(studentService.listAll());
    }
    //GET BY ID

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {

        return ResponseEntity.of(studentService.findById(id));
    }

    //PUT
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody StudentDTO studentDTO) {

         return ResponseEntity.ok(studentService.update(id, studentDTO));

    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
