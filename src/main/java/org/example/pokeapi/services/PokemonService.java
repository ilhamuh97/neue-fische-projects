package org.example.pokeapi.services;

import lombok.RequiredArgsConstructor;
import org.example.pokeapi.exceptions.PokemonAlreadyExistsException;
import org.example.pokeapi.exceptions.PokemonNotFoundException;
import org.example.pokeapi.models.pokemon.FavoritePokemon;
import org.example.pokeapi.models.pokemon.Pokemon;
import org.example.pokeapi.models.pokemon.PokemonPostDTO;
import org.example.pokeapi.models.pokemon.PokemonUpdateDTO;
import org.example.pokeapi.models.pokemon.rawPokemon.RawPokemon;
import org.example.pokeapi.repos.PokemonRepo;
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
    private final IdService idService;
    private final PokemonRepo pokemonRepo;

    // POKEMON API
    public Pokemon getPokemonByName(String name) {
        RawPokemon rawPokemon = getRawPokemon(name);

        return Pokemon.builder()
                .pokemonId(rawPokemon.id())
                .pokemonName(rawPokemon.name())
                .height(rawPokemon.height())
                .weight(rawPokemon.weight())
                .pictureUrl(getPictureUrl(rawPokemon))
                .types(getTypes(rawPokemon))
                .build();
    }

    public FavoritePokemon addPokemonToCollection(PokemonPostDTO pokemonDTO) {
        RawPokemon rawPokemon = getRawPokemon(pokemonDTO.pokemonName());

        Boolean pokemonExists  = pokemonRepo.existsByPokemonId(rawPokemon.id());

        if (pokemonExists) {
            throw new PokemonAlreadyExistsException("Pokemon already exists in collection: " + rawPokemon.name());
        }

        FavoritePokemon favPokemon = FavoritePokemon.builder()
                .id(idService.randomId())
                .pokemonId(rawPokemon.id())
                .nickName(pokemonDTO.nickname())
                .pokemonName(rawPokemon.name())
                .pictureUrl(getPictureUrl(rawPokemon))
                .height(rawPokemon.height())
                .weight(rawPokemon.weight())
                .types(getTypes(rawPokemon))
                .build();

        return  pokemonRepo.save(favPokemon);
    }

    // COLLECTION
    public FavoritePokemon getFavPokemonById(String id) {
        return pokemonRepo.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found!"));
    }

    public void deleteFavPokemonById(String id) {
        boolean pokemonExists  = pokemonRepo.existsById(id);

        if (!pokemonExists) {
            throw new PokemonNotFoundException("Pokemon not found!");
        }

        pokemonRepo.deleteById(id);
    }

    public List<FavoritePokemon> getAllFavPokemon() {
        return pokemonRepo.findAll();
    }

    public FavoritePokemon updateFavPokemonNicknameById(String id, PokemonUpdateDTO pokemonDTO) {
        FavoritePokemon pokemonExists = pokemonRepo.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found!"));

        FavoritePokemon favPokemon = FavoritePokemon.builder()
                .id(pokemonExists.id())
                .pokemonId(pokemonExists.pokemonId())
                .nickName(pokemonDTO.nickname())
                .pokemonName(pokemonExists.pokemonName())
                .pictureUrl(pokemonExists.pictureUrl())
                .height(pokemonExists.height())
                .weight(pokemonExists.weight())
                .types(pokemonExists.types())
                .build();

        return  pokemonRepo.save(favPokemon);
    }

    // UTILS
    private RawPokemon getRawPokemon(String name) {
        return Objects.requireNonNull(restClientPokemon.get()
                .uri("/pokemon/{name}", name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (_, _) -> {
                    throw new PokemonNotFoundException("Pokemon not found!");
                })
                .body(RawPokemon.class));
    }

    private String getPictureUrl(RawPokemon rawPokemon) {
        return rawPokemon.sprites().other().officialArtwork().frontDefault();
    }

    private List<String> getTypes(RawPokemon rawPokemon) {
        return rawPokemon.types().stream().map(t -> t.type().name()).toList();
    }
}
