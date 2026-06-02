package org.example.pokeapi.models.rawPokemon;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpritesOtherDTO(
        @JsonProperty("official-artwork")
        SpritesOtherOfficialArtwork officialArtwork) {
}
