package com.pragma.technology.application.dto.request;

import java.util.List;

public record CapacityTechnologiesRequestDto(
        Long capacityId,
        List<Long> technologyIds
) {
}
