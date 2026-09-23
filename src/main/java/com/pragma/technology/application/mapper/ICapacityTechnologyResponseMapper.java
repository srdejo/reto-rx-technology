package com.pragma.technology.application.mapper;

import com.pragma.technology.application.dto.response.CapacityTechnologiesResponseDto;
import com.pragma.technology.domain.model.CapacityTechnologiesModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = ITechnologyResponseMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICapacityTechnologyResponseMapper {
    CapacityTechnologiesResponseDto toResponse(CapacityTechnologiesModel capacityTechnologiesModel);
}
