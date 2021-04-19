package com.invillia.SchoolProject.service;

import com.invillia.SchoolProject.dto.request.ClassroomDTO;
import com.invillia.SchoolProject.dto.request.StudentDTO;
import com.invillia.SchoolProject.model.Classroom;
import com.invillia.SchoolProject.model.Student;
import com.invillia.SchoolProject.repository.ClassroomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClassroomServiceTest {

    @InjectMocks
    private ClassroomService classroomService;

    @Mock
    private ClassroomRepository classroomRepository;

        @Test
        public void checkIfClassroomIsSavedTest(){

            Classroom classroom = new Classroom(1l, "2021");
            ClassroomDTO classroomNotSaved = new ClassroomDTO(1L,"2021");

            when(classroomRepository.save(any(Classroom.class))).thenReturn(classroom);

            ClassroomDTO expected = new ClassroomDTO(1L,"2021");
            Classroom actual = classroomService.save(expected);

            assertEquals(expected.getId(), actual.getId());
            assertEquals(expected.getName(), actual.getName());

        }

    @Test
    public void checkIfListAllClassroomsTest() {

        //Devo testar o tamanho da lista?

        Classroom classroom = new Classroom(1L, "2021");
        ClassroomDTO classroomDTO = new ClassroomDTO(1l, "2021");

        List<Classroom> expected = new ArrayList<>();
        expected.add(classroom);

        when(classroomRepository.findAll()).thenReturn(expected);

        classroomService.save(classroomDTO);

        List<Classroom> actual = classroomService.listAll();

        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void checkIfFindsClassroomByIdTest() {

        Classroom classroom = new Classroom(1L, "2021");
        ClassroomDTO classroomDTO = new ClassroomDTO(1l, "2021");

        when(classroomRepository.findById(classroomDTO.getId())).thenReturn(java.util.Optional.of(classroom));

        //Devo converter actual para Optional para funcionar? findById
        Optional<Classroom> expected = classroomService.findById(classroomDTO.getId());
        Optional <Classroom> actual = Optional.of(classroom);
        assertEquals(expected, actual);
    }

    @Test
    public void checkIfDeletesStudentByIdTest(){

        Classroom classroom = new Classroom(1L, "2021");
        ClassroomDTO classroomDTO = new ClassroomDTO(1l, "2021");

        classroomService.deleteById(classroomDTO.getId());
        verify(classroomRepository, times(1)).deleteById(classroom.getId());
    }



}
