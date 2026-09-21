package com.pragma.technology.domain.usecase;

import com.pragma.technology.domain.api.ITechnologyServicePort;
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
        return technologyPersistencePort.saveTechnology(technologyModel);
    }

    @Override
    public Flux<TechnologyModel> getAllTechnologys() {
        return technologyPersistencePort.getAllTechnologys();
    }
}
