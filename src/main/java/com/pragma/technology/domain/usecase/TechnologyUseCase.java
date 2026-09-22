package com.pragma.technology.domain.usecase;

import com.pragma.technology.domain.api.ITechnologyServicePort;
import com.pragma.technology.domain.exception.TechnologyAlreadyExistsException;
import com.pragma.technology.domain.model.TechnologyModel;
import com.pragma.technology.domain.spi.ITechnologyPersistencePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TechnologyUseCase implements ITechnologyServicePort {

    private final ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
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
}
