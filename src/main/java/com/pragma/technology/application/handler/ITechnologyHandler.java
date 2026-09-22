package com.pragma.technology.application.handler;

import com.pragma.technology.application.dto.request.CapacityTechnologiesRequestDto;
import com.pragma.technology.application.dto.request.TechnologyRequestDto;
import com.pragma.technology.application.dto.response.TechnologyResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITechnologyHandler {

    Mono<Void> saveTechnology(TechnologyRequestDto technologyRequestDto);

    Flux<TechnologyResponseDto> getAllTechnologies();

    Mono<Void> saveCapacityTechnologies(CapacityTechnologiesRequestDto capacityTechnologiesRequestDto);
}
