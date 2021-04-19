package com.invillia.SchoolProject.mapper;

import com.invillia.SchoolProject.dto.request.ClassroomDTO;
import com.invillia.SchoolProject.model.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClassroomMapper {


        ClassroomMapper INSTANCE = Mappers.getMapper(com.invillia.SchoolProject.mapper.ClassroomMapper.class);

        Classroom toModel(ClassroomDTO classroomDTO);

        ClassroomDTO toDTO(Classroom classroom);
    }


