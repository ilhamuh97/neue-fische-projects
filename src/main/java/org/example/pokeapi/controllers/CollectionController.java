package org.example.pokeapi.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.pokeapi.models.pokemon.FavoritePokemon;
import org.example.pokeapi.models.pokemon.PokemonDTO;
import org.example.pokeapi.services.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collection")
@AllArgsConstructor
public class CollectionController {
    private PokemonService pokemonService;

    @PostMapping()
    public ResponseEntity<FavoritePokemon> addPokemonToCollection(@RequestBody @Valid PokemonDTO pokemonTDO) {
        return ResponseEntity.ok().body(pokemonService.addPokemonToCollection(pokemonTDO));
    }

}
