package com.pragma.technology.application.mapper;

import com.pragma.technology.application.dto.response.TechnologyResponseDto;
import com.pragma.technology.domain.model.TechnologyModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITechnologyResponseMapper {
    TechnologyResponseDto toResponse(TechnologyModel technologyModel);
}
