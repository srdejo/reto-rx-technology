package com.pragma.technology.infrastructure.input.rest;

import com.pragma.technology.application.dto.request.TechnologyRequestDto;
import com.pragma.technology.application.dto.response.TechnologyResponseDto;
import com.pragma.technology.application.handler.ITechnologyHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/technology")
@RequiredArgsConstructor
public class TechnologyRestController {

    private final ITechnologyHandler technologyHandler;

    @Operation(summary = "Add a new technology")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Technology created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @PostMapping()
    public Mono<ResponseEntity<Void>> saveTechnology(@Valid @RequestBody TechnologyRequestDto technologyRequestDto) {
        return technologyHandler.saveTechnology(technologyRequestDto)
                .thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
    }

    @Operation(summary = "Get all technologys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All technologys returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TechnologyResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping()
    public Flux<TechnologyResponseDto> getAllTechnologies() {
        return technologyHandler.getAllTechnologies();
    }
}
