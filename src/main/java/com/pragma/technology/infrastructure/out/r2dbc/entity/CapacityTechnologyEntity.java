package com.pragma.technology.infrastructure.out.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("capacity_technology")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CapacityTechnologyEntity {

    @Id
    private Long id;

    @Column("technology_id")
    private Long technologyId;

    @Column("capacity_id")
    private Long capacityId;

    public CapacityTechnologyEntity(Long technologyId, Long capacityId) {
        this.technologyId = technologyId;
        this.capacityId = capacityId;
    }
}
