package com.example.pokedex.api

import com.example.pokedex.models.FinalDataResponse

import com.example.pokedex.models.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApi {

    //get pokemon list
//    @GET("https://pokeapi.co/api/v2/pokemon?limit=69")
//    suspend fun getPokemonListData():Response<PokemonList>
    @GET("https://pokeapi.co/api/v2/pokemon?limit=300")
    suspend fun getPokemonListData( ):Response<PokemonList>

    // get details of the pokemon
    @GET()
    suspend fun getPokemonDetails(@Url url: String):Response<FinalDataResponse>




}