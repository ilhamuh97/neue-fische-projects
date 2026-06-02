package org.example.pokeapi.models.pokemon.rawPokemon;

import java.util.List;

public record RawPokemon(
        int id,
        String name,
        int height,
        int weight,
        List<TypeAndSlotDTO> types,
        SpritesDTO sprites) {
}
