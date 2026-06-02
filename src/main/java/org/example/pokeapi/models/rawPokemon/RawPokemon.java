package org.example.pokeapi.models.rawPokemon;

import java.util.List;

public record RawPokemon(
        String id,
        String name,
        int height,
        int weight,
        List<TypeAndSlotDTO> types,
        SpritesDTO sprites) {
}
