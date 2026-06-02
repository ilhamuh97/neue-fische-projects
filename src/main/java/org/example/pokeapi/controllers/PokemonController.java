package org.example.pokeapi.controllers;

import lombok.AllArgsConstructor;
import org.example.pokeapi.models.Pokemon;
import org.example.pokeapi.services.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemon")
@AllArgsConstructor
public class PokemonController {
    private PokemonService pokemonService;

    @GetMapping("/{name}")
    public ResponseEntity<Pokemon> getPokemonByName(@PathVariable String name) {
        return ResponseEntity.ok().body(pokemonService.getPokemonByName(name));
    }
}
