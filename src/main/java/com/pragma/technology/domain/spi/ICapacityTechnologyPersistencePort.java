package com.pragma.technology.domain.spi;

import com.pragma.technology.domain.model.CapacityTechnologiesModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICapacityTechnologyPersistencePort {
    Mono<Void> saveCapacityTechnologies(Long capacityId, List<Long> technologyIds);

    Flux<CapacityTechnologiesModel> getTechnologiesByCapacityIds(List<Long> capacityIds);

    Flux<Long> findTechnologyIdsByCapacityIds(List<Long> capacityIds);

    Mono<Void> deleteByCapacityIds(List<Long> capacityIds);

    Flux<Long> findReferencedTechnologyIds(List<Long> technologyIds);
}
