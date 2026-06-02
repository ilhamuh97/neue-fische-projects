package org.example.pokeapi.repos;

import org.example.pokeapi.models.pokemon.FavoritePokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepo extends MongoRepository<FavoritePokemon, String> {

    Boolean existsByPokemonId(int pokemonId);
}
