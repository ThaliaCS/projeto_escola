package com.invillia.SchoolProject.mapper;

import com.invillia.SchoolProject.dto.request.StudentDTO;
import com.invillia.SchoolProject.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper MAPPING = Mappers.getMapper(StudentMapper.class);

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toModel(StudentDTO studentDTO);

    StudentDTO toDTO(Student student);

}
