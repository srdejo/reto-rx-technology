package com.pragma.technology.domain;

import com.pragma.technology.domain.model.TechnologyModel;
import com.pragma.technology.domain.spi.ITechnologyPersistencePort;
import com.pragma.technology.domain.usecase.TechnologyUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class TechnologyUseCaseTest {

    private final ITechnologyPersistencePort port = Mockito.mock(ITechnologyPersistencePort.class);
    private final TechnologyUseCase useCase = new TechnologyUseCase(port);

    @Test
    void saveTechnologyDelegatesToPort() {
        TechnologyModel model = new TechnologyModel(null, "test");
        Mockito.when(port.saveTechnology(model)).thenReturn(Mono.just(model));

        StepVerifier.create(useCase.saveTechnology(model)).expectNext(model).verifyComplete();
    }

    @Test
    void getAllTechnologysReturnsFlux() {
        TechnologyModel model = new TechnologyModel(null, "test");
        Mockito.when(port.getAllTechnologys()).thenReturn(Flux.just(model));

        StepVerifier.create(useCase.getAllTechnologys()).expectNext(model).verifyComplete();
    }
}
