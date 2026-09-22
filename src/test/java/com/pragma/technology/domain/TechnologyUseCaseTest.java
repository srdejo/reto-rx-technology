package com.pragma.technology.domain;

import com.pragma.technology.domain.model.TechnologyModel;
import com.pragma.technology.domain.spi.ITechnologyPersistencePort;
import com.pragma.technology.domain.usecase.TechnologyUseCase;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TechnologyUseCaseTest {

    private final ITechnologyPersistencePort port = mock(ITechnologyPersistencePort.class);
    private final TechnologyUseCase useCase = new TechnologyUseCase(port);

    @Test
    void saveTechnologyDelegatesToPort() {
        TechnologyModel model = new TechnologyModel(null, "test", "description");
        when(port.saveTechnology(model)).thenReturn(Mono.just(model));

        StepVerifier.create(useCase.saveTechnology(model)).expectNext(model).verifyComplete();
    }

    @Test
    void getAllTechnologysReturnsFlux() {
        TechnologyModel model = new TechnologyModel(null, "test", "description");
        when(port.getAllTechnologies()).thenReturn(Flux.just(model));

        StepVerifier.create(useCase.getAllTechnologies()).expectNext(model).verifyComplete();
    }
}
