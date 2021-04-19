package com.invillia.SchoolProject.service;

import com.invillia.SchoolProject.dto.request.MentorDTO;
import com.invillia.SchoolProject.mapper.MentorMapper;
import com.invillia.SchoolProject.model.Mentor;
import com.invillia.SchoolProject.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {

    @Autowired
    MentorRepository mentorRepository;

    private final MentorMapper mentorMapper = MentorMapper.MAPPING.INSTANCE;

//    public MentorService(ClassroomRepository classRepository) {
//        this.mentorRepository = mentorRepository;
//    }

    public Mentor save(MentorDTO mentorDTO) {

        Mentor mentorToSave = mentorMapper.toModel(mentorDTO);
        Mentor savedMentor = mentorRepository.save(mentorToSave);

        return mentorRepository.save(savedMentor);
    }

    public List<Mentor> findAll() {
        return mentorRepository.findAll();
    }


    public MentorDTO save(Mentor record) {

        Mentor savedMentor = mentorRepository.save(record);

        return null;
    }

    public Object update(Long id, MentorDTO mentorDTO) {

        return mentorRepository.findById(id)
                .map(record -> {
                    record.setName(mentorDTO.getName());
                    record.setLastname(mentorDTO.getLastname());
                    Mentor updated = mentorRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public void deleteById(Long id) {

        mentorRepository.deleteById(id);
    }

    public Optional<Mentor> findById(Long mentor) {

        return mentorRepository.findById(mentor);
    }

    public Mentor findOne(Long id) {
        return mentorRepository.findById(id).orElse(null);
    }


}
