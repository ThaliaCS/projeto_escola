package com.invillia.SchoolProject.service;

import com.invillia.SchoolProject.dto.request.MentoringDTO;
import com.invillia.SchoolProject.exception.mentorWithMoreThan3StudentsException;
import com.invillia.SchoolProject.exception.repeatedStudentException;
import com.invillia.SchoolProject.model.Classroom;
import com.invillia.SchoolProject.model.Mentor;
import com.invillia.SchoolProject.model.Mentoring;
import com.invillia.SchoolProject.model.Student;
import com.invillia.SchoolProject.repository.MentoringRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
        Student student1 = new Student(1l,"Ana", "Silva", classroom);
        Mentor mentor = new Mentor(1l, "Carlos", "Souza");

        MentoringDTO mentoringToSave1 = new MentoringDTO(1L, 1L ,1L);
        MentoringDTO mentoringToSave2 = new MentoringDTO(2L, 1L, 1L);

        when(mentoringRepository.existsById(anyLong())).thenReturn(true);

        assertThrows( repeatedStudentException.class, ()-> mentoringService.save(mentoringToSave2));

    }

    private void extracted(Student student, Mentor mentor, MentoringDTO mentoringToSave1) {
        Mentoring expected1 = new Mentoring(1L, mentor, student);
        when(mentoringRepository.save(any(Mentoring.class))).thenReturn(expected1);
        assertEquals(mentoringService.save(mentoringToSave1),  expected1);
    }

    @Test
    public void mustReturnNullIfMentorHasMoreThan3MentoringTest() {

        //mentor e student id
        Classroom classroom = new Classroom(1l, "2021");
        Student student1 = new Student(5L,"Ana", "Silva", classroom);
        Student student2 = new Student(6L,"Ana", "Silva", classroom);
        Student student3 = new Student(7L,"Ana", "Silva", classroom);
        Student student4 = new Student(8L,"Ana", "Silva", classroom);

        Mentor mentor1 = new Mentor(1l, "Paulo", "Silva");

        MentoringDTO expectedMentoring1 = new MentoringDTO(1L, mentor1.getId(), student1.getId());
        MentoringDTO expectedMentoring2 = new MentoringDTO(1L, mentor1.getId(), student2.getId());
        MentoringDTO expectedMentoring3 = new MentoringDTO(1L, mentor1.getId(), student3.getId());
        MentoringDTO expectedMentoring4 = new MentoringDTO(1L, mentor1.getId(), student4.getId());

        //when(mentoringRepository.save(any(Mentoring.class))).thenReturn(mentoring);
        Mentoring actualOk1  = mentoringService.save(expectedMentoring1);
        Mentoring actualOk2  = mentoringService.save(expectedMentoring2);
        Mentoring actualOk3  = mentoringService.save(expectedMentoring3);
        assertThrows( mentorWithMoreThan3StudentsException.class, ()-> mentoringService.save(expectedMentoring4));

        assertEquals(expectedMentoring1,  actualOk1);

    }


    @Test
    public void checkIfListAllMentoringsTest() {

        Classroom classroom = new Classroom(1L, "2021");
        Student student = new Student(1L,"Ana", "Silva", classroom);
        Student student1 = new Student(1L,"Ana", "Silva", classroom);
        Mentor mentor = new Mentor(1L, "Carlos", "Souza");

        Mentoring expected = new Mentoring(1L, mentor, student);

        MentoringDTO mentoringToSave1 = new MentoringDTO(1L, 1L ,1L);

        List<Mentoring> expecteds = new ArrayList<>();
        expecteds.add(expected);
        when(mentoringRepository.findAll()).thenReturn(expecteds);
        mentoringService.save(mentoringToSave1);
        List<MentoringDTO> actual = mentoringService.listAll();
        assertEquals(expecteds.size(), actual.size());
    }

    @Test
    public void checkIfFindsMentoringById(){

        Classroom classroom = new Classroom(1L, "2021");
        Student student = new Student(1L,"Ana", "Silva", classroom);
        Student student1 = new Student(1L,"Ana", "Silva", classroom);
        Mentor mentor = new Mentor(1L, "Carlos", "Souza");

        Mentoring mentoring = new Mentoring(1L, mentor, student);

        MentoringDTO mentoringToSave1 = new MentoringDTO(1L, 1L ,1L);

        when(mentoringRepository.findById(mentoringToSave1.getId())).thenReturn(Optional.of(mentoring));

        //Devo converter actual para Optional para funcionar? findById
        Optional<Mentoring> expected = (Optional<Mentoring>) mentoringService.findById(mentoringToSave1.getId());
        Optional <Mentoring> actual = Optional.of(mentoring);
        assertEquals(expected, actual);

    }
}
