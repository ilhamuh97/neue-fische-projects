package org.example.pokeapi.models.pokemon.rawPokemon;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpritesOtherDTO(
        @JsonProperty("official-artwork")
        SpritesOtherOfficialArtwork officialArtwork) {
}
