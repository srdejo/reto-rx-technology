package com.pragma.technology.infrastructure.configuration;

import com.pragma.technology.domain.api.ITechnologyServicePort;
import com.pragma.technology.domain.spi.ITechnologyPersistencePort;
import com.pragma.technology.domain.usecase.TechnologyUseCase;
import com.pragma.technology.infrastructure.out.r2dbc.adapter.TechnologyAdapter;
import com.pragma.technology.infrastructure.out.r2dbc.mapper.ITechnologyEntityMapper;
import com.pragma.technology.infrastructure.out.r2dbc.repository.ITechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {
        return new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }

    @Bean
    public ITechnologyServicePort technologyServicePort() {
        return new TechnologyUseCase(technologyPersistencePort());
    }
}
