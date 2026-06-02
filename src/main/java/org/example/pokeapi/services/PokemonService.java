package org.example.pokeapi.services;

import lombok.RequiredArgsConstructor;
import org.example.pokeapi.exceptions.PokemonNotFoundException;
import org.example.pokeapi.models.Pokemon;
import org.example.pokeapi.models.rawPokemon.RawPokemon;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PokemonService {
    private final RestClient restClientPokemon;

    public Pokemon getPokemonByName(String name) {

        RawPokemon rawPokemon = Objects.requireNonNull(restClientPokemon.get()
                .uri("/pokemon/{name}", name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (_, _) -> {
                    throw new PokemonNotFoundException("Pokemon not found!");
                })
                .body(RawPokemon.class));

        return Pokemon.builder()
                .pokemonId((rawPokemon).id())
                .pokemonName(rawPokemon.name())
                .height(rawPokemon.height())
                .weight(rawPokemon.weight())
                .pictureUrl(getPictureUrl(rawPokemon))
                .types(getTypes(rawPokemon))
                .build();
    }

    private String getPictureUrl(RawPokemon rawPokemon) {
        return rawPokemon.sprites().other().officialArtwork().frontDefault();
    }

    private List<String> getTypes(RawPokemon rawPokemon) {
        return rawPokemon.types().stream().map(t -> t.type().name()).toList();
    }
}
