package com.pragma.technology.domain.spi;

import com.pragma.technology.domain.model.TechnologyModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyPersistencePort {
    Mono<TechnologyModel> saveTechnology(TechnologyModel technologyModel);

    Flux<TechnologyModel> getAllTechnologies();

    Mono<Boolean> existByName(String name);

    Flux<Long> findExistingTechnologyIds(List<Long> technologyIds);
}
