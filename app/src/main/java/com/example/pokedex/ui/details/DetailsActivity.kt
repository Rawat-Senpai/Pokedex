package com.example.pokedex.ui.details

import android.content.Context
import android.content.Intent

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.databinding.ActivityDetailsBinding
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.models.MappedData
import com.skydoves.transformationlayout.TransformationAppCompatActivity
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout

class DetailsActivity : TransformationAppCompatActivity() {

    private var _binding: ActivityDetailsBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_details)

        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<MappedData>(pokemonName)?.let {
            Glide.with(this)
                .load(it.imageUrl)
                .into(binding.image)
//            binding.detailTitle.text = it.name
//            binding.detailDescription.text = it.description
        }


    }

    companion object{

        const val pokemonName = "pokemonName"

        fun startActivity(
            context:Context,
            transformationLayout:TransformationLayout,
            pokemon:MappedData
        ){
            val intent = Intent(context,DetailsActivity::class.java)
            TransformationCompat.startActivity(transformationLayout,intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding=null
    }

}