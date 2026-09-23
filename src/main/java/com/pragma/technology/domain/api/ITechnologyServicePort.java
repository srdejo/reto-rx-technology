package com.pragma.technology.domain.api;

import com.pragma.technology.domain.model.TechnologyModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITechnologyServicePort {

    Mono<TechnologyModel> saveTechnology(TechnologyModel technologyModel);

    Flux<TechnologyModel> getAllTechnologies();
}
