package com.invillia.SchoolProject.service;

import com.invillia.SchoolProject.dto.request.ClassroomDTO;
import com.invillia.SchoolProject.mapper.ClassroomMapper;
import com.invillia.SchoolProject.model.Classroom;
import com.invillia.SchoolProject.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassroomService {

    @Autowired
    ClassroomRepository classroomRepository;

    private final ClassroomMapper classroomMapper = ClassroomMapper.INSTANCE;

    public ClassroomService(ClassroomRepository classroomRepository) { this.classroomRepository = classroomRepository;}

    public Classroom save(ClassroomDTO classroomDTO){

        Classroom classroomToSave = classroomMapper.toModel(classroomDTO);

        Classroom savedClassroom = classroomRepository.save(classroomToSave);

        return classroomRepository.save(classroomToSave);
    }

    public List<Classroom> listAll() {
        List<Classroom> allClassrooms = classroomRepository.findAll();
        return  allClassrooms;
    }


    public ClassroomDTO save(Classroom record) {

        Classroom savedClassroom = classroomRepository.save(record);

        return null;
    }

    public Optional<Classroom> findById(Long id) {

        return classroomRepository.findById(id);
    }

    public void deleteById(Long id) {

        classroomRepository.deleteById(id);
    }

    public Object update(Long id, ClassroomDTO classroomDTO) {

        return classroomRepository.findById(id)
                .map(record -> {
                    record.setName(classroomDTO.getName());
                    Classroom updated = classroomRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }



    public Classroom findOne(Long id) {
        return classroomRepository.findById(id).orElse(null);
    }


    public boolean existsById(Long classroom_id) {

       return classroomRepository.existsById(classroom_id);
    }
}
