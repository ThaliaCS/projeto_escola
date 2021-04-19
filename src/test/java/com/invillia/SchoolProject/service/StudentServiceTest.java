package com.invillia.SchoolProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.invillia.SchoolProject.dto.request.StudentDTO;
import com.invillia.SchoolProject.model.Classroom;
import com.invillia.SchoolProject.model.Student;
import com.invillia.SchoolProject.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ClassroomService classroomService;

    @Test
    public void checkIfStudentIsSavedTest() {

        Classroom classroom = new Classroom(1l, "2021");
        Student student = new Student(1l, "Ana", "Silva", classroom);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDTO expected = new StudentDTO(1L, "Ana", "Silva", classroom.getId());
        Student actual = studentService.save(expected);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLastname(), actual.getLastname());
        assertEquals(expected.classroom_id, actual.getClassroom().getId());
    }

    //listAll
    @Test
    public void checkIfListAllStudentsTest() {

        //Devo testar o tamanho da lista?

        Classroom classroom = new Classroom(1L, "2021");
        StudentDTO studentNotSaved = new StudentDTO(1L, "Ana", "Silva", classroom.getId());
        Student student = new Student(1l, "Ana", "Silva", classroom);

        List<Student> expected = new ArrayList<>();
        expected.add(student);

        when(studentRepository.findAll()).thenReturn(expected);

        studentService.save(studentNotSaved);

        List<Student> actual = studentService.listAll();

        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void checkIfFindsStudentByIdTest() {

        Classroom classroom = new Classroom(1L, "2021");
        StudentDTO studentNotSaved = new StudentDTO(1L, "Ana", "Silva", classroom.getId());
        Student student = new Student(1l, "Ana", "Silva", classroom);

        when(studentRepository.findById(studentNotSaved.getId())).thenReturn(java.util.Optional.of(student));

        //Devo converter actual para Optional para funcionar? findById
        Optional<Student> expected = studentService.findById(studentNotSaved.getId());
        Optional <Student> actual = Optional.of(student);
        assertEquals(expected, actual);
    }

    @Test
    public void checkIfDeletesStudentByIdTest(){

        Classroom classroom = new Classroom(1L, "2021");
        StudentDTO studentNotSaved = new StudentDTO(1L, "Ana", "Silva", classroom.getId());
        Student student = new Student(1l, "Ana", "Silva", classroom);

        studentService.deleteById(studentNotSaved.getId());
        verify(studentRepository, times(1)).deleteById(student.getId());
    }
}
