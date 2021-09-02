package com.pokemon.pokemonapi.controller;

import com.pokemon.pokemonapi.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    /**
     * Bütün pokemonların ismini ve image urlsini döndüren rest call yazılmıştır
     * Beşerli liste halinde döndürmektedir ve sonraki ve önceki beşerli listenin linkleri döndürülmektedir
     * Offset kaçıncı elementin dönmesini belirler
     * @return PokemonRequestDto
     */
    @GetMapping(path = "/getPokemonsFrom/{offset}")
    public ResponseEntity getAllPokemon(@PathVariable String offset){
        return ResponseEntity.ok(pokemonService.getAllPokemons(offset));
    }

    /**
     * Görüntülenmek istenen pokemonun url'de id veya isim göndererek ilgili alanları getiren rest call yazılmıştır
     *
     * @return PokemonRequestDto
     */
    @GetMapping(path = "/getPokemonByName/{pokemon}")
    public ResponseEntity getPokemonByName(@PathVariable String pokemon){
        return ResponseEntity.ok(pokemonService.getPokemon(pokemon));
    }
}
