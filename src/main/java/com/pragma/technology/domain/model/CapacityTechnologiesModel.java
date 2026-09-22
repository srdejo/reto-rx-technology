package com.pragma.technology.domain.model;

import java.util.List;

public record CapacityTechnologiesModel(
        Long capacityId,
        List<TechnologyModel> technologies
) {
}
