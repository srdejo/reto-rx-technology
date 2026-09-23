package com.pragma.technology.application.handler.impl;

import com.pragma.technology.application.dto.request.CapacityTechnologiesRequestDto;
import com.pragma.technology.application.dto.response.CapacityTechnologiesResponseDto;
import com.pragma.technology.application.handler.ICapacityTechnologyHandler;
import com.pragma.technology.application.mapper.ICapacityTechnologyResponseMapper;
import com.pragma.technology.domain.api.ICapacityTechnologyServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CapacityTechnologyHandler implements ICapacityTechnologyHandler {

    private final ICapacityTechnologyServicePort capacityTechnologyServicePort;
    private final ICapacityTechnologyResponseMapper capacityTechnologyResponseMapper;

    @Override
    @Transactional
    public Mono<Void> saveCapacityTechnologies(CapacityTechnologiesRequestDto capacityTechnologiesRequestDto) {
        return capacityTechnologyServicePort.saveCapacityTechnologies(
                capacityTechnologiesRequestDto.capacityId(),
                capacityTechnologiesRequestDto.technologyIds());
    }

    @Override
    public Flux<CapacityTechnologiesResponseDto> getTechnologiesByCapacityIds(List<Long> capacityIds) {
        return capacityTechnologyServicePort.getTechnologiesByCapacityIds(capacityIds)
                .map(capacityTechnologyResponseMapper::toResponse);
    }

    @Override
    @Transactional
    public Mono<Void> deleteTechnologiesByCapacityIds(List<Long> capacityIds) {
        return capacityTechnologyServicePort.deleteTechnologiesByCapacityIds(capacityIds);
    }
}
