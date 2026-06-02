package org.example.pokeapi.exceptions;

import com.mongodb.lang.Nullable;
import org.example.pokeapi.models.errors.ErrorResponseDTO;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                            @NonNull HttpHeaders headers,
                                                                            @NonNull HttpStatusCode status,
                                                                            @NonNull WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<FieldError> allErrors = ex.getBindingResult().getFieldErrors();

        allErrors.forEach(error -> {
            String fieldName = error.getField();
            String errorMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, errorMsg);
        });

        return ResponseEntity
                .status(status.value())
                .body(validationErrors);
    }

    @ExceptionHandler(PokemonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handlePokemonNotFoundException(PokemonNotFoundException ex, WebRequest request) {
        HttpStatus errorStatus = HttpStatus.NOT_FOUND;

        return ErrorResponseDTO.builder()
                .path(request.getDescription(false).replace("uri=", ""))
                .message(ex.getMessage())
                .status(errorStatus.value())
                .error(errorStatus.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(PokemonAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDTO handlePokemonAlreadyExistsException(PokemonAlreadyExistsException ex, WebRequest request) {
        HttpStatus errorStatus = HttpStatus.CONFLICT;

        return ErrorResponseDTO.builder()
                .path(request.getDescription(false).replace("uri=", ""))
                .message(ex.getMessage())
                .status(errorStatus.value())
                .error(errorStatus.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(CollectionEntryNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDTO handleCollectionEntryNotFoundException(CollectionEntryNotFoundException ex, WebRequest request) {
        HttpStatus errorStatus = HttpStatus.CONFLICT;

        return ErrorResponseDTO.builder()
                .path(request.getDescription(false).replace("uri=", ""))
                .message(ex.getMessage())
                .status(errorStatus.value())
                .error(errorStatus.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
