package com.invillia.SchoolProject.controller;
import com.invillia.SchoolProject.dto.request.MentoringDTO;
import com.invillia.SchoolProject.service.MentoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/mentoring")
public class MentoringController {

    @Autowired
    private MentoringService mentoringService;

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity <List<MentoringDTO>> save(@RequestBody MentoringDTO mentoringDTO){

      if(mentoringService.save(mentoringDTO) !=null){
          return new ResponseEntity<>(HttpStatus.CREATED);
      } else {
          return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
      }
    }

    //GET
    @GetMapping
    public ResponseEntity <List<MentoringDTO>> listAll() {
        return ResponseEntity.ok(mentoringService.listAll());

    }

    //GET BY ID
    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(mentoringService.findById(id));
    }

    //PUT
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody MentoringDTO mentoringDTO) {

        return ResponseEntity.ok(mentoringService.update(id, mentoringDTO));

    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        mentoringService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

