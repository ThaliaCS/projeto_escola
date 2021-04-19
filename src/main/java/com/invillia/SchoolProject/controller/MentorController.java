package com.invillia.SchoolProject.controller;

import com.invillia.SchoolProject.dto.request.MentorDTO;
import com.invillia.SchoolProject.model.Mentor;
import com.invillia.SchoolProject.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/mentors")
public class MentorController {

    @Autowired
    private MentorService mentorService;


    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity save(@RequestBody @Valid MentorDTO mentorDTO){

        mentorService.save(mentorDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //GET
    @GetMapping
    public ResponseEntity <List<Mentor>> findAll(){

        return ResponseEntity.ok(mentorService.findAll());

    }

    //GT BY ID
    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {

        return ResponseEntity.of(mentorService.findById(id));
    }


    //PUT
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody MentorDTO mentorDTO) {

        return ResponseEntity.ok(mentorService.update(id, mentorDTO));
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        mentorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}