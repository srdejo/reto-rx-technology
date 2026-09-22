package com.pragma.technology.infrastructure.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    INVALID_REQUEST("Invalid request body"),
    TECHNOLOGY_ALREADY_EXISTS("Technology already exists"),
    ;

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

}
