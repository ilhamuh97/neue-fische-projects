package org.example.pokeapi.models.errors;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDTO(String apiPath, HttpStatus errorCode, String errorMsg, LocalDateTime errorTime) {
}
