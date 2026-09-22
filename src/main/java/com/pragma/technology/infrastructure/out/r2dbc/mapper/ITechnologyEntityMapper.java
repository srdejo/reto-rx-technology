package com.pragma.technology.infrastructure.out.r2dbc.mapper;

import com.pragma.technology.domain.model.TechnologyModel;
import com.pragma.technology.infrastructure.out.r2dbc.entity.TechnologyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ITechnologyEntityMapper {

    TechnologyEntity toEntity(TechnologyModel technologyModel);

    TechnologyModel toTechnologyModel(TechnologyEntity technologyEntity);
}
