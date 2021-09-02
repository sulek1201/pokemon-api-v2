package com.pokemon.pokemonapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonListDto {

    private String name;
    private String url;
    private String imageUrl;
}
