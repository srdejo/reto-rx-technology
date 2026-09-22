package com.pragma.technology.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnologyRequestDto {
    @NotBlank(message = "name must not be blank")
    @Size(max = 50, message = "name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "description must not be blank")
    @Size(max = 90, message = "description must not exceed 50 characters")
    private String description;

}
