package com.pokemon.pokemonapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonRequestDto {
    private int count;
    private String next;
    private String previous;
    private List<PokemonListDto> results;
}
