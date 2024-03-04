package com.example.pokedex.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding:ActivityMainBinding?= null
    private val binding get() = _binding!!
    private val pokemonViewModel by viewModels<PokemonViewModel>()
    private lateinit var adapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter= PokemonListAdapter()
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.adapter= adapter

        bindObserver()

    }


    private fun bindObserver() {


        pokemonViewModel.pokemonNameCopyLiveData.observe(this ,Observer{ it->
            binding.progressBar.isVisible=false
            when(it){
                is NetworkResult.Error -> {
                    Toast.makeText(this,"Something went wrong ",Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible=true
                }
                is NetworkResult.Success -> {
                    adapter.submitList(it.data)
                }
            }
            Log.d("checking_Shobhit",it.toString())
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}

