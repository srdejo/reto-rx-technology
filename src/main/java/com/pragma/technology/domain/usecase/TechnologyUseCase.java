package com.pragma.technology.domain.usecase;

import com.pragma.technology.domain.api.ITechnologyServicePort;
import com.pragma.technology.domain.exception.TechnologyAlreadyExistsException;
import com.pragma.technology.domain.exception.TechnologyNotFoundException;
import com.pragma.technology.domain.model.CapacityTechnologiesModel;
import com.pragma.technology.domain.model.TechnologyModel;
import com.pragma.technology.domain.spi.ICapacityTechnologyPersistencePort;
import com.pragma.technology.domain.spi.ITechnologyPersistencePort;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Transactional
public class TechnologyUseCase implements ITechnologyServicePort {

    private final ITechnologyPersistencePort technologyPersistencePort;
    private final ICapacityTechnologyPersistencePort capacityTechnologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort,
                              ICapacityTechnologyPersistencePort capacityTechnologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
        this.capacityTechnologyPersistencePort = capacityTechnologyPersistencePort;
    }

    @Override
    public Mono<TechnologyModel> saveTechnology(TechnologyModel technologyModel) {
        return technologyPersistencePort
                .existByName(technologyModel.getName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new TechnologyAlreadyExistsException());
                    }

                    return technologyPersistencePort.saveTechnology(technologyModel);
                });
    }

    @Override
    public Flux<TechnologyModel> getAllTechnologies() {
        return technologyPersistencePort.getAllTechnologies();
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
}
