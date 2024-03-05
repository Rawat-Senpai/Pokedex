package com.example.pokedex.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokedex.api.PokemonApi

import com.example.pokedex.models.Results
import com.example.pokedex.models.details.PokemonInfoModel
import com.example.pokedex.utils.NetworkResult
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


class PokemonRepository @Inject constructor(private val pokemonAPi: PokemonApi) {

    private val _pokemonName = MutableLiveData<NetworkResult<ArrayList<Results>>>()
    val pokemonNameResponse: LiveData<NetworkResult<ArrayList<Results>>>
        get() =   _pokemonName

    suspend fun getPokemonName( ) {
        _pokemonName.postValue(NetworkResult.Loading())

        val response = pokemonAPi.getPokemonListData( )

        if (response.isSuccessful && response.body() != null) {
            _pokemonName.postValue(NetworkResult.Success(response.body()!!.results))
        } else if (response.errorBody() != null) {
            _pokemonName.postValue(NetworkResult.Error("Something went wrong"))
        } else {
            _pokemonName.postValue(NetworkResult.Error("Something went wrong"))
        }


        Log.d("checkingResponse", response.toString())
        Log.d("checkingResponse", response.body()?.results.toString())

    }

    private val _pokemonDetails = MutableLiveData<NetworkResult<PokemonInfoModel>>()
    val pokemonDetailResponse: LiveData<NetworkResult<PokemonInfoModel>>
        get() = _pokemonDetails


    suspend fun getPokemonDetail(url: String) {

        coroutineScope {

            _pokemonDetails.postValue(NetworkResult.Loading())
            val response = pokemonAPi.getPokemonDetails(url)
            if (response.isSuccessful && response.body() != null) {
                _pokemonDetails.postValue(NetworkResult.Success(response.body()!!))
            } else if (response.errorBody() != null) {
                _pokemonDetails.postValue(NetworkResult.Error("Something went wrong"))
            } else {
                _pokemonDetails.postValue(NetworkResult.Error("Something went wrong"))
            }
            Log.d("checkingResponse", response.toString())
            Log.d("checkingResponse", response.body().toString())
        }
    }

}