package com.pragma.technology.infrastructure.out.r2dbc.repository;

import com.pragma.technology.infrastructure.out.r2dbc.entity.CapacityTechnologyEntity;
import com.pragma.technology.infrastructure.out.r2dbc.projection.CapacityTechnologyProjection;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ICapacityTechnologyRepository extends ReactiveCrudRepository<CapacityTechnologyEntity, Long> {

    @Query("""
            SELECT ct.capacity_id AS capacity_id, t.technology_id AS technology_id, t.name AS name, t.description AS description
            FROM capacity_technology ct
            JOIN technology t ON t.technology_id = ct.technology_id
            WHERE ct.capacity_id IN (:capacityIds)
            """)
    Flux<CapacityTechnologyProjection> findTechnologiesByCapacityIds(List<Long> capacityIds);
}
