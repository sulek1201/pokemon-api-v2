package com.pokemon.pokemonapi.service.impl;

import com.pokemon.pokemonapi.dto.*;
import com.pokemon.pokemonapi.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${pokemon.server.url}")
    private String pokemonServerUrl;


    private static final int pageLimit = 5;



    @Override
    public PokemonRequestDto getAllPokemons(String offset) {
        String url = "https://pokeapi.co/api/v2/pokemon?offset" + offset + "&limit=" + String.valueOf(pageLimit);
        PokemonRequestDto pokemonRequestDto = restTemplate.getForObject(url, PokemonRequestDto.class);
        setImagesForPokemons(pokemonRequestDto); // Imageler için apiden dönen url değerlerine tekrar rest call yapılıp image linkleri getirilmiştir
        setNextPage(pokemonRequestDto, offset); // Bir sonraki 5 elementli list linki verilmiştir
        setPreviousPage(pokemonRequestDto,offset); // Bir önceki 5 elementli list linki verilmiştir
        return pokemonRequestDto;
    }

    @Override
    public PokemonResponseDto getPokemon(String pokemon) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + pokemon;
        PokemonDto pokemonDto = restTemplate.getForObject(url, PokemonDto.class);
        PokemonResponseDto pokemonResponseDto = new PokemonResponseDto();
        pokemonResponseDto.setName(pokemonDto.getSpecies().getName());
        pokemonResponseDto.setImageUrl(pokemonDto.getSprites().getBack_default());
        pokemonResponseDto.setAbilities(pokemonDto.getAbilities());
        return pokemonResponseDto;
    }



    public void setImagesForPokemons(PokemonRequestDto pokemonRequestDto) {
        for(PokemonListDto pokemonListDto : pokemonRequestDto.getResults()){
            SpritesDto spritesDto = restTemplate.getForObject(pokemonListDto.getUrl(), SpritesDto.class);
            pokemonListDto.setImageUrl(spritesDto.getSprites().getBack_default());
        }
    }

    public void setNextPage(PokemonRequestDto pokemonRequestDto, String offset){
        int nextPage = Integer.valueOf(offset) + pageLimit;
        pokemonRequestDto.setNext(pokemonServerUrl+ "getPokemonsFrom/" + String.valueOf(nextPage));
    }

    public void setPreviousPage(PokemonRequestDto pokemonRequestDto, String offset){
        int previousPage = Integer.valueOf(offset) - pageLimit;

        if(previousPage < 0){ // Offsetin eksiden başlaması engellenmiştir
            previousPage = 0;
        }
        pokemonRequestDto.setPrevious(pokemonServerUrl+ "getPokemonsFrom/" + String.valueOf(previousPage));
    }
}
