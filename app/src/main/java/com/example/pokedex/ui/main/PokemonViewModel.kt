package com.example.pokedex.ui.main

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.pokedex.models.MappedData
import com.example.pokedex.models.Results
import com.example.pokedex.repository.PokemonRepository
import com.example.pokedex.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val _pokemonNameCopyLiveData = MutableLiveData<NetworkResult<ArrayList<MappedData>>>()
    val pokemonNameCopyLiveData: LiveData<NetworkResult<ArrayList<MappedData>>> get() = _pokemonNameCopyLiveData

    init {
        observePokemonNameLiveData()
        getPokemonList()
    }

    private fun observePokemonNameLiveData() {
        pokemonNameLiveData.observeForever { result ->
            when(result){
                is NetworkResult.Error -> {
                    _pokemonNameCopyLiveData.postValue(NetworkResult.Error("Something went Wrong"))
                }
                is NetworkResult.Loading ->{
                    _pokemonNameCopyLiveData.postValue(NetworkResult.Loading())
                }
                is NetworkResult.Success ->{
                    result?.data?.let { pokemonList ->
                        val mappedDataList = transformPokemonList(pokemonList)
                        _pokemonNameCopyLiveData.postValue(NetworkResult.Success( mappedDataList))
                    }
                }
            }
        }
    }

    //            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"
    private fun transformPokemonList(pokemonList: ArrayList<Results>): ArrayList<MappedData> {
        val mappedDataList = ArrayList<MappedData>()
        pokemonList.forEach { pokemon ->
            val number = extractPokemonNumber(pokemon.url)
            val url =   "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                    "pokemon/other/official-artwork/$number.png"
            mappedDataList.add(MappedData(pokemon.name.toString(), url, number.toString(),pokemon.url.toString()))
        }
        return mappedDataList
    }

    private fun extractPokemonNumber(url: String?): String {
        return url?.let {
            if (it.endsWith("/")) {
                it.dropLast(1).takeLastWhile { char -> char.isDigit() }
            } else {
                it.takeLastWhile { char -> char.isDigit() }
            }
        } ?: ""
    }

    fun getPokemonList() {
        viewModelScope.launch {
            pokemonRepository.getPokemonName()
        }
    }

    fun getPokemonDetail(url: String) {
        viewModelScope.launch {
            pokemonRepository.getPokemonDetail(url)
        }
    }

    val pokemonNameLiveData: LiveData<NetworkResult<ArrayList<Results>>> get() = pokemonRepository.pokemonNameResponse



}
