package com.pragma.technology.infrastructure.out.r2dbc.adapter;

import com.pragma.technology.domain.model.TechnologyModel;
import com.pragma.technology.domain.spi.ITechnologyPersistencePort;
import com.pragma.technology.infrastructure.exception.NoDataFoundException;
import com.pragma.technology.infrastructure.out.r2dbc.entity.TechnologyEntity;
import com.pragma.technology.infrastructure.out.r2dbc.mapper.ITechnologyEntityMapper;
import com.pragma.technology.infrastructure.out.r2dbc.repository.ITechnologyRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {

    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Override
    public Mono<TechnologyModel> saveTechnology(TechnologyModel technologyModel) {
        return technologyRepository.save(technologyEntityMapper.toEntity(technologyModel))
                .map(technologyEntityMapper::toTechnologyModel);
    }

    @Override
    public Flux<TechnologyModel> getAllTechnologies() {
        return technologyRepository.findAll()
                .switchIfEmpty(Flux.error(new NoDataFoundException()))
                .map(technologyEntityMapper::toTechnologyModel);
    }

    @Override
    public Mono<Boolean> existByName(String name) {
        return technologyRepository.existsByName(name);
    }

    @Override
    public Flux<Long> findExistingTechnologyIds(List<Long> technologyIds) {
        return technologyRepository.findAllById(technologyIds)
                .map(TechnologyEntity::getId);
    }
}
