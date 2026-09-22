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
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {
        return new TechnologyAdapter(technologyRepository, technologyEntityMapper, r2dbcEntityTemplate);
    }

    @Bean
    public ITechnologyServicePort technologyServicePort() {
        return new TechnologyUseCase(technologyPersistencePort());
    }
}
