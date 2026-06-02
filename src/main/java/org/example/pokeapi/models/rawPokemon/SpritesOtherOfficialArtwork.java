package org.example.pokeapi.models.rawPokemon;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpritesOtherOfficialArtwork(
        @JsonProperty("front_default")
        String frontDefault
) {}
