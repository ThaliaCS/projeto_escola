package com.invillia.SchoolProject.mapper;

import com.invillia.SchoolProject.dto.request.MentorDTO;
import com.invillia.SchoolProject.model.Mentor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MentorMapper {

    MentorMapper MAPPING = Mappers.getMapper(MentorMapper.class);

    MentorMapper INSTANCE = Mappers.getMapper(MentorMapper.class);

    Mentor toModel(MentorDTO mentorDTO);

    MentorDTO toDTO(Mentor mentor);
}
