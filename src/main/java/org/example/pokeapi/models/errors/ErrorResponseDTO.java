package org.example.pokeapi.models.errors;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDTO(String path, int status, String error, LocalDateTime timestamp, String message) {
}
