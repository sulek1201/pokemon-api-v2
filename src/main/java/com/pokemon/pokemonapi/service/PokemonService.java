package com.pokemon.pokemonapi.service;


import com.pokemon.pokemonapi.dto.PokemonRequestDto;
import com.pokemon.pokemonapi.dto.PokemonResponseDto;

public interface PokemonService {

   PokemonRequestDto getAllPokemons(String offset);
   PokemonResponseDto getPokemon(String pokemon);

}
