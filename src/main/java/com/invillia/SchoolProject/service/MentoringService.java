package com.invillia.SchoolProject.service;

import com.invillia.SchoolProject.dto.request.MentoringDTO;
import com.invillia.SchoolProject.exception.mentorWithMoreThan3StudentsException;
import com.invillia.SchoolProject.exception.repeatedStudentException;
import com.invillia.SchoolProject.mapper.MentoringMapper;
import com.invillia.SchoolProject.model.Mentoring;
import com.invillia.SchoolProject.repository.MentoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentoringService {

    @Autowired
    private MentoringRepository mentoringRepository;

    @Autowired
    private MentorService mentorService;

    @Autowired
    private StudentService studentService;

    private final MentoringMapper mentoringMapper = MentoringMapper.INSTANCE;

    public Mentoring save(MentoringDTO mentoringDTO) {

        //Verifica se aluno já tem uma mentoria
        if (mentoringRepository.existsById(mentoringDTO.getStudent_id())) {
            throw new repeatedStudentException("Student already saved in mentoring!");
        }

        //Verifica se mentor tem mais de 3 mentorias
        List<Mentoring> mentorExistingMentoring = mentoringRepository.findAll().stream()
                .filter(mentoring -> mentoring.getMentor().getId() == mentoringDTO.getMentor_id())
                .collect(Collectors.toList());

        if(mentorExistingMentoring.size() > 2) {
            throw new mentorWithMoreThan3StudentsException("Mentor cannot have more than 3 students!");
        }

        mentoringDTO.setMentor(mentorService.findOne(mentoringDTO.getMentor_id()));
        mentoringDTO.setStudent(studentService.findOne(mentoringDTO.getStudent_id()));

        Mentoring mentoringToSave = mentoringMapper.toModel(mentoringDTO);
        Mentoring savedMentoring = mentoringRepository.save(mentoringToSave);

        return  mentoringRepository.save(savedMentoring);
    }

    public List<MentoringDTO> listAll() {
        List<Mentoring> allMentorings = mentoringRepository.findAll();
        return allMentorings.stream()
                .map(mentoringMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        mentoringRepository.deleteById(id);
    }

    public Object findById(Long id) {
        return  mentoringRepository.findById(id);
    }

    public Object update(Long id, MentoringDTO mentoringDTO) {

        return mentoringRepository.findById(id).
                map(mentoring -> {
                    mentoring.setMentor(mentoringDTO.getMentor());
                    mentoring.setStudent(mentoringDTO.getStudent());
                    Mentoring updated = mentoringRepository.save(mentoring);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


}





