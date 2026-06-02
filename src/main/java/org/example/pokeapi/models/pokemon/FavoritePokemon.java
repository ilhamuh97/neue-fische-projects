package org.example.pokeapi.models.pokemon;

import lombok.Builder;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;

import java.util.List;

@Builder
public record FavoritePokemon(
        @Id String id,

        @UniqueElements int pokemonId,

        String nickName,

        String pokemonName,
        String pictureUrl,

        int height,
        int weight,

        List<String> types) {
}
