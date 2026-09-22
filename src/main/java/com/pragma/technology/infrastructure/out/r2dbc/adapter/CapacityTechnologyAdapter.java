package com.pragma.technology.infrastructure.out.r2dbc.adapter;

import com.pragma.technology.domain.model.CapacityTechnologiesModel;
import com.pragma.technology.domain.model.TechnologyModel;
import com.pragma.technology.domain.spi.ICapacityTechnologyPersistencePort;
import com.pragma.technology.infrastructure.out.r2dbc.entity.CapacityTechnologyEntity;
import com.pragma.technology.infrastructure.out.r2dbc.projection.CapacityTechnologyProjection;
import com.pragma.technology.infrastructure.out.r2dbc.repository.ICapacityTechnologyRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CapacityTechnologyAdapter implements ICapacityTechnologyPersistencePort {

    private final ICapacityTechnologyRepository capacityTechnologyRepository;

    @Override
    public Mono<Void> saveCapacityTechnologies(Long capacityId, List<Long> technologyIds) {
        return Flux.fromIterable(technologyIds)
                .map(technologyId -> new CapacityTechnologyEntity(technologyId, capacityId))
                .as(capacityTechnologyRepository::saveAll)
                .then();
    }

    @Override
    public Flux<CapacityTechnologiesModel> getTechnologiesByCapacityIds(List<Long> capacityIds) {
        if (capacityIds.isEmpty()) {
            return Flux.empty();
        }

        return capacityTechnologyRepository.findTechnologiesByCapacityIds(capacityIds)
                .collectMultimap(CapacityTechnologyProjection::capacityId, this::toTechnologyModel)
                .flatMapMany(technologiesByCapacityId -> Flux.fromIterable(capacityIds)
                        .map(capacityId -> new CapacityTechnologiesModel(
                                capacityId,
                                new ArrayList<>(technologiesByCapacityId.getOrDefault(capacityId, List.of())))));
    }

    private TechnologyModel toTechnologyModel(CapacityTechnologyProjection projection) {
        return new TechnologyModel(projection.technologyId(), projection.name(), projection.description());
    }
}
