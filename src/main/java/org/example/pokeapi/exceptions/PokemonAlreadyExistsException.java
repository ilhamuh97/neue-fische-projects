package org.example.pokeapi.exceptions;

public class PokemonAlreadyExistsException extends RuntimeException {
    public PokemonAlreadyExistsException(String message) {
        super(message);
    }
}