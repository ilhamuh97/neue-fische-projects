package org.example.pokeapi.models;

import lombok.Builder;

import java.util.List;

@Builder
public record Pokemon(
        String pokemonId,
        String pokemonName,
        int height,
        int weight,
        String pictureUrl,
        List<String> types) {
}
