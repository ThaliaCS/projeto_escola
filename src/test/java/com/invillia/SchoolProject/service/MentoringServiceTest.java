package com.invillia.SchoolProject.service;

import com.invillia.SchoolProject.dto.request.ClassroomDTO;
import com.invillia.SchoolProject.dto.request.MentoringDTO;
import com.invillia.SchoolProject.dto.request.StudentDTO;
import com.invillia.SchoolProject.model.Classroom;
import com.invillia.SchoolProject.model.Mentor;
import com.invillia.SchoolProject.model.Mentoring;
import com.invillia.SchoolProject.model.Student;
import com.invillia.SchoolProject.repository.ClassroomRepository;
import com.invillia.SchoolProject.repository.MentorRepository;
import com.invillia.SchoolProject.repository.MentoringRepository;
import com.invillia.SchoolProject.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MentoringServiceTest {

    @InjectMocks
    private MentoringService mentoringService;

    @Mock
    private MentorService mentorService;

    @Mock
    private StudentService studentService;

    @Mock
    private MentoringRepository mentoringRepository;

    @Test
    public void checkIfMentoringIsSavedTest(){

        //mentor e student id
        Classroom classroom = new Classroom(1l, "2021");
        Student student = new Student(1l,"Ana", "Silva", classroom);
        Mentor mentor = new Mentor(1l, "Paulo", "Silva");

        Mentoring mentoring = new Mentoring(1L, mentor, student);

        when(mentoringRepository.save(any(Mentoring.class))).thenReturn(mentoring);

        MentoringDTO expected = new MentoringDTO(1L, mentor.getId(), student.getId());
        Mentoring expected1 = new Mentoring(1L, mentor, student);
        Mentoring actual  = mentoringService.save(expected);
        assertEquals(expected1.getId(), actual.getId());
        assertEquals(expected1.getMentor(), actual.getMentor());
        assertEquals(expected1.getStudent(), actual.getStudent());
    }

    @Test
    public void mustReturnNullIfStudentExistsTwiceInMentoringTest() {

        //mentor e student id
        Classroom classroom = new Classroom(1l, "2021");
        Student student = new Student(1l,"Ana", "Silva", classroom);
        Mentor mentor = new Mentor(1l, "Carlos", "Souza");

        MentoringDTO mentoringToSave1 = new MentoringDTO(1L, 1L ,1L);
        MentoringDTO mentoringToSave2 = new MentoringDTO(2L, 1L, 1L);

        Mentoring expected1 = new Mentoring(1L, mentor, student);
        when(mentoringRepository.save(any(Mentoring.class))).thenReturn(expected1);
        assertEquals(mentoringService.save(mentoringToSave1),  expected1);

        Mentoring expected2 = new Mentoring(2L, mentor, student);
        when(mentoringRepository.save(any(Mentoring.class))).thenReturn(null);
        assertEquals(mentoringService.save(mentoringToSave2),  null);
    }

    @Test
    public void mustReturnNullIfMentorHasMoreThan3MentoringTest() {

        //mentor e student id
        Classroom classroom = new Classroom(1l, "2021");
        Student student1 = new Student(5L,"Ana", "Silva", classroom);


        Mentor mentor1 = new Mentor(1l, "Paulo", "Silva");

        MentoringDTO expectedMentoring1 = new MentoringDTO(1L, mentor1.getId(), student1.getId());

        //when(mentoringRepository.save(any(Mentoring.class))).thenReturn(mentoring);
        Mentoring actualOk1  = mentoringService.save(expectedMentoring1);

//        Mentoring actualOk2  = mentoringService.save(mentoring2);
//        Mentoring actualOk3  = mentoringService.save(mentoring3);
//        Mentoring actualNull = mentoringService.save(mentoring4);
          assertEquals(expectedMentoring1,  actualOk1);
//        assertEquals(mentoring2.getId(),  actualOk2.getId());
//        assertEquals(mentoring3.getId(),  actualOk3.getId());
//        assertEquals(mentoring4,  actualNull);

    }
}
