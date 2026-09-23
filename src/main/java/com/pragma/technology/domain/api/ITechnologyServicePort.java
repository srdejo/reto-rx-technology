package com.pragma.technology.domain.api;

import com.pragma.technology.domain.model.CapacityTechnologiesModel;
import com.pragma.technology.domain.model.TechnologyModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyServicePort {

    Mono<TechnologyModel> saveTechnology(TechnologyModel technologyModel);

    Flux<TechnologyModel> getAllTechnologies();

    Mono<Void> saveCapacityTechnologies(Long capacityId, List<Long> technologyIds);

    Flux<CapacityTechnologiesModel> getTechnologiesByCapacityIds(List<Long> capacityIds);

    Mono<Void> deleteTechnologiesByCapacityIds(List<Long> capacityIds);
}
