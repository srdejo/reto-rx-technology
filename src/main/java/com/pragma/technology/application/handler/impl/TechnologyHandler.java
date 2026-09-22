package com.pragma.technology.application.handler.impl;

import com.pragma.technology.application.dto.request.CapacityTechnologiesRequestDto;
import com.pragma.technology.application.dto.request.TechnologyRequestDto;
import com.pragma.technology.application.dto.response.TechnologyResponseDto;
import com.pragma.technology.application.handler.ITechnologyHandler;
import com.pragma.technology.application.mapper.ITechnologyRequestMapper;
import com.pragma.technology.application.mapper.ITechnologyResponseMapper;
import com.pragma.technology.domain.api.ITechnologyServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TechnologyHandler implements ITechnologyHandler {

    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyRequestMapper technologyRequestMapper;
    private final ITechnologyResponseMapper technologyResponseMapper;

    @Override
    public Mono<Void> saveTechnology(TechnologyRequestDto technologyRequestDto) {
        return technologyServicePort.saveTechnology(technologyRequestMapper.toTechnology(technologyRequestDto)).then();
    }

    @Override
    public Flux<TechnologyResponseDto> getAllTechnologies() {
        return technologyServicePort.getAllTechnologies().map(technologyResponseMapper::toResponse);
    }

    @Override
    public Mono<Void> saveCapacityTechnologies(CapacityTechnologiesRequestDto capacityTechnologiesRequestDto) {
        return technologyServicePort.saveCapacityTechnologies(
                capacityTechnologiesRequestDto.capacityId(),
                capacityTechnologiesRequestDto.technologyIds());
    }
}
