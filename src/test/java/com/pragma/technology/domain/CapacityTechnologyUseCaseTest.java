package com.pragma.technology.domain;

import com.pragma.technology.domain.exception.TechnologyNotFoundException;
import com.pragma.technology.domain.model.CapacityTechnologiesModel;
import com.pragma.technology.domain.model.TechnologyModel;
import com.pragma.technology.domain.spi.ICapacityTechnologyPersistencePort;
import com.pragma.technology.domain.spi.ITechnologyPersistencePort;
import com.pragma.technology.domain.usecase.CapacityTechnologyUseCase;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CapacityTechnologyUseCaseTest {

    private final ITechnologyPersistencePort technologyPort = mock(ITechnologyPersistencePort.class);
    private final ICapacityTechnologyPersistencePort capacityTechnologyPort = mock(ICapacityTechnologyPersistencePort.class);
    private final CapacityTechnologyUseCase useCase = new CapacityTechnologyUseCase(technologyPort, capacityTechnologyPort);

    @Test
    void saveCapacityTechnologiesSavesWhenAllTechnologiesExist() {
        List<Long> technologyIds = List.of(1L, 2L);
        when(technologyPort.findExistingTechnologyIds(technologyIds)).thenReturn(Flux.just(1L, 2L));
        when(capacityTechnologyPort.saveCapacityTechnologies(10L, technologyIds)).thenReturn(Mono.empty());

        StepVerifier.create(useCase.saveCapacityTechnologies(10L, technologyIds)).verifyComplete();

        verify(capacityTechnologyPort).saveCapacityTechnologies(10L, technologyIds);
    }

    @Test
    void saveCapacityTechnologiesFailsWhenSomeTechnologyDoesNotExist() {
        List<Long> technologyIds = List.of(1L, 2L);
        when(technologyPort.findExistingTechnologyIds(technologyIds)).thenReturn(Flux.just(1L));

        StepVerifier.create(useCase.saveCapacityTechnologies(10L, technologyIds))
                .expectError(TechnologyNotFoundException.class)
                .verify();

        verify(capacityTechnologyPort, never()).saveCapacityTechnologies(any(), any());
    }

    @Test
    void getTechnologiesByCapacityIdsReturnsFlux() {
        CapacityTechnologiesModel model = new CapacityTechnologiesModel(10L,
                List.of(new TechnologyModel(1L, "Java", "description")));
        when(capacityTechnologyPort.getTechnologiesByCapacityIds(List.of(10L))).thenReturn(Flux.just(model));

        StepVerifier.create(useCase.getTechnologiesByCapacityIds(List.of(10L))).expectNext(model).verifyComplete();
    }

    @Test
    void deleteTechnologiesByCapacityIdsDeletesOnlyOrphanTechnologies() {
        List<Long> capacityIds = List.of(10L);
        when(capacityTechnologyPort.findTechnologyIdsByCapacityIds(capacityIds)).thenReturn(Flux.just(1L, 2L));
        when(capacityTechnologyPort.deleteByCapacityIds(capacityIds)).thenReturn(Mono.empty());
        when(capacityTechnologyPort.findReferencedTechnologyIds(List.of(1L, 2L))).thenReturn(Flux.just(2L));
        when(technologyPort.deleteTechnologiesByIds(List.of(1L))).thenReturn(Mono.empty());

        StepVerifier.create(useCase.deleteTechnologiesByCapacityIds(capacityIds)).verifyComplete();

        verify(technologyPort).deleteTechnologiesByIds(List.of(1L));
    }

    @Test
    void deleteTechnologiesByCapacityIdsDoesNothingWhenListIsEmpty() {
        StepVerifier.create(useCase.deleteTechnologiesByCapacityIds(List.of())).verifyComplete();

        verify(capacityTechnologyPort, never()).deleteByCapacityIds(any());
    }
}
