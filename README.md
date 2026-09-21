# technology-api

Reactive microservice (Spring WebFlux, R2DBC + MySQL) based on the Pragma hexagonal archetype.

- Port: 8084
- Endpoints: POST and GET on /api/v1/technology/
- Swagger: http://localhost:8084/swagger-ui.html
- Run: ./gradlew bootRun
- Env: `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD`
