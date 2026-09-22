CREATE TABLE IF NOT EXISTS technology (
    technology_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(90) NOT NULL
);

CREATE TABLE IF NOT EXISTS capacity_technology (
    technology_id BIGINT,
    capacity_id BIGINT,
    PRIMARY KEY (technology_id, capacity_id)
)