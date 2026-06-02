package org.example.pokeapi.exceptions;

public class CollectionEntryNotFoundException extends RuntimeException{
    public CollectionEntryNotFoundException(String message) {
        super(message);
    }
}
