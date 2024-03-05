package com.example.pokedex.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.models.details.PokemonInfoModel
import com.example.pokedex.repository.PokemonRepository
import com.example.pokedex.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :ViewModel() {


     fun getPokemonDetail(url:String){
        viewModelScope.launch {
            pokemonRepository.getPokemonDetail(url)
        }
    }

    val pokemonDetailData: LiveData<NetworkResult<PokemonInfoModel>> get() = pokemonRepository.pokemonDetailResponse


}