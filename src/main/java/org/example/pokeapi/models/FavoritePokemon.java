package org.example.pokeapi.models;

import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.util.List;

@Builder
public record FavoritePokemon(
        @Id int id,

        int pokemonId,

        String nickName,

        String pokemonName,
        String pictureUrl,

        int height,
        int weight,

        List<String> types) {
}
