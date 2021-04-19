package com.invillia.SchoolProject.mapper;

import com.invillia.SchoolProject.dto.request.MentoringDTO;
import com.invillia.SchoolProject.model.Mentoring;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MentoringMapper {

    MentoringMapper MAPPING = Mappers.getMapper(MentoringMapper.class);

    MentoringMapper INSTANCE = Mappers.getMapper(MentoringMapper.class);

    Mentoring toModel(MentoringDTO mentoringDTO);

    MentoringDTO toDTO(Mentoring mentoring);

}
