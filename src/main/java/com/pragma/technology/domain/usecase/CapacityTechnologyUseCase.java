package com.pragma.technology.domain.usecase;

import com.pragma.technology.domain.api.ICapacityTechnologyServicePort;
import com.pragma.technology.domain.exception.TechnologyNotFoundException;
import com.pragma.technology.domain.model.CapacityTechnologiesModel;
import com.pragma.technology.domain.spi.ICapacityTechnologyPersistencePort;
import com.pragma.technology.domain.spi.ITechnologyPersistencePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class CapacityTechnologyUseCase implements ICapacityTechnologyServicePort {

    private final ITechnologyPersistencePort technologyPersistencePort;
    private final ICapacityTechnologyPersistencePort capacityTechnologyPersistencePort;

    public CapacityTechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort,
                                     ICapacityTechnologyPersistencePort capacityTechnologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
        this.capacityTechnologyPersistencePort = capacityTechnologyPersistencePort;
    }

    @Override
    public Mono<Void> saveCapacityTechnologies(Long capacityId, List<Long> technologyIds) {
        return technologyPersistencePort.findExistingTechnologyIds(technologyIds)
                .collectList()
                .flatMap(existingIds -> {
                    if (existingIds.size() != technologyIds.size()) {
                        return Mono.error(new TechnologyNotFoundException());
                    }
                    return capacityTechnologyPersistencePort.saveCapacityTechnologies(capacityId, technologyIds);
                });
    }

    @Override
    public Flux<CapacityTechnologiesModel> getTechnologiesByCapacityIds(List<Long> capacityIds) {
        return capacityTechnologyPersistencePort.getTechnologiesByCapacityIds(capacityIds);
    }

    @Override
    public Mono<Void> deleteTechnologiesByCapacityIds(List<Long> capacityIds) {
        if (capacityIds.isEmpty()) {
            return Mono.empty();
        }

        return capacityTechnologyPersistencePort.findTechnologyIdsByCapacityIds(capacityIds)
                .collectList()
                .flatMap(technologyIds -> capacityTechnologyPersistencePort.deleteByCapacityIds(capacityIds)
                        .then(deleteOrphanTechnologies(technologyIds)));
    }

    private Mono<Void> deleteOrphanTechnologies(List<Long> technologyIds) {
        if (technologyIds.isEmpty()) {
            return Mono.empty();
        }

        return capacityTechnologyPersistencePort.findReferencedTechnologyIds(technologyIds)
                .collectList()
                .flatMap(stillReferencedIds -> {
                    List<Long> orphanTechnologyIds = technologyIds.stream()
                            .filter(technologyId -> !stillReferencedIds.contains(technologyId))
                            .toList();
                    if (orphanTechnologyIds.isEmpty()) {
                        return Mono.empty();
                    }
                    return technologyPersistencePort.deleteTechnologiesByIds(orphanTechnologyIds);
                });
    }
}
