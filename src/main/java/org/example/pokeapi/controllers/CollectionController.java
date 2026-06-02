package org.example.pokeapi.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.pokeapi.models.pokemon.FavoritePokemon;
import org.example.pokeapi.models.pokemon.PokemonPostDTO;
import org.example.pokeapi.models.pokemon.PokemonUpdateDTO;
import org.example.pokeapi.services.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/collection")
@AllArgsConstructor
public class CollectionController {
    private PokemonService pokemonService;

    @GetMapping()
    public ResponseEntity<List<FavoritePokemon>> getAllFavPokemon() {
        return ResponseEntity.ok().body(pokemonService.getAllFavPokemon());
    }

    @PostMapping()
    public ResponseEntity<FavoritePokemon> addPokemonToCollection(@RequestBody @Valid PokemonPostDTO pokemonTDO) {
        FavoritePokemon createdFavPokemon = pokemonService.addPokemonToCollection(pokemonTDO);
        URI location = URI.create("/api/collection/" + createdFavPokemon.id());

        return ResponseEntity.created(location).body(createdFavPokemon);
    }

    @GetMapping("{id}")
    public ResponseEntity<FavoritePokemon> getFavPokemonById(@PathVariable String id) {
        return ResponseEntity.ok().body(pokemonService.getFavPokemonById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFavPokemonById(@PathVariable String id) {
        pokemonService.deleteFavPokemonById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<FavoritePokemon> updateFavPokemonNicknameById(@PathVariable String id, @RequestBody @Valid PokemonUpdateDTO pokemonTDO) {
        return ResponseEntity.ok().body(pokemonService.updateFavPokemonNicknameById(id, pokemonTDO));
    }
}
