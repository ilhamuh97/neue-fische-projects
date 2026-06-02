package org.example.pokeapi.models.pokemon;

import lombok.Builder;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Builder
public record Pokemon(
        @UniqueElements int pokemonId,

        String pokemonName,

        int height,
        int weight,

        String pictureUrl,
        List<String> types) {
}
