package com.pokemon.pokemonapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonDto {

    private List<Abilities> abilities;
    private Species species;
    private Sprites sprites;
}
