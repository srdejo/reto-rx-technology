package com.pragma.technology.application.handler;

import com.pragma.technology.application.dto.request.CapacityTechnologiesRequestDto;
import com.pragma.technology.application.dto.response.CapacityTechnologiesResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICapacityTechnologyHandler {

    Mono<Void> saveCapacityTechnologies(CapacityTechnologiesRequestDto capacityTechnologiesRequestDto);

    Flux<CapacityTechnologiesResponseDto> getTechnologiesByCapacityIds(List<Long> capacityIds);

    Mono<Void> deleteTechnologiesByCapacityIds(List<Long> capacityIds);
}
