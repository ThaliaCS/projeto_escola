package com.invillia.SchoolProject.service;

import com.invillia.SchoolProject.dto.request.MentorDTO;
import com.invillia.SchoolProject.dto.request.StudentDTO;
import com.invillia.SchoolProject.model.Classroom;
import com.invillia.SchoolProject.model.Mentor;
import com.invillia.SchoolProject.model.Student;
import com.invillia.SchoolProject.repository.ClassroomRepository;
import com.invillia.SchoolProject.repository.MentorRepository;
import com.invillia.SchoolProject.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MentorServiceTest {

        @InjectMocks
        private MentorService mentorService;

        @Mock
        private MentorRepository mentorRepository;

        @Test
        public void checkIfMentorIsSavedTest(){

            Mentor mentor = new Mentor(1l,"Ana", "Silva");
            MentorDTO mentorNotSaved = new MentorDTO(1l,"Ana", "Silva");

            when(mentorRepository.save(any(Mentor.class))).thenReturn(mentor);

            MentorDTO expected = new MentorDTO(1l,"Ana", "Silva");
            Mentor actual = mentorService.save(expected);

            assertEquals(expected.getId(), actual.getId());
            assertEquals(expected.getName(), actual.getName());
            assertEquals(expected.getLastname(), actual.getLastname());
        }

        @Test
        public void checkIfListAllMentorTest(){

            List<Mentor> expected = new ArrayList<>();
            expected.add(new Mentor(1l, "Jośe", "Carlos"));
            expected.add(new Mentor(2l, "Maria", "Silva"));

            MentorDTO mentorNotSaved = new MentorDTO(1l,"Ana", "Silva");
            MentorDTO mentorNotSaved1 = new MentorDTO(1l,"Ana", "Silva");

            when(mentorRepository.findAll()).thenReturn(expected);
            mentorService.save(mentorNotSaved);
            mentorService.save(mentorNotSaved1);

            List<Mentor> actual = mentorService.findAll();

            assertEquals(expected.size(), actual.size());

        }

        @Test
        public void checkIfFindsMentorById(){

            MentorDTO mentorDTO = new MentorDTO(1l,"Ana", "Silva");
            Mentor mentor = new Mentor(1l,"Ana", "Silva");

            when(mentorRepository.findById(mentorDTO.getId())).thenReturn(java.util.Optional.of(mentor));

            //Devo converter actual para Optional para funcionar? findById
            Optional<Mentor> expected = mentorService.findById(mentorDTO.getId());
            Optional <Mentor> actual = Optional.of(mentor);
            assertEquals(expected, actual);

        }


    }
