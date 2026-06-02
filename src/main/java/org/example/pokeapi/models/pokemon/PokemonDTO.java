package org.example.pokeapi.models.pokemon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PokemonDTO(
        @NotBlank(message="Pokemon name must not be blank")
        String pokemonName,

        @NotBlank(message="Pokemon name must not be blank")
        @Size(min=5, max=15, message="Nick name must be between 5 and 15 characters")
        String nickname) {
}
