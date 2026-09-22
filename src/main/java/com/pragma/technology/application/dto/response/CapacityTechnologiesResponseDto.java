package com.pragma.technology.application.dto.response;

import java.util.List;

public record CapacityTechnologiesResponseDto(
        Long capacityId,
        List<TechnologyResponseDto> technologies
) {
}
