package com.example.pokedex.ui.details

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex.databinding.ActivityDetailsBinding
import com.example.pokedex.models.MappedData
import com.example.pokedex.models.details.PokemonInfoModel
import com.example.pokedex.utils.BaseUtils
import com.example.pokedex.utils.NetworkResult
import com.example.pokedex.utils.SpacesItemDecoration
import com.skydoves.androidribbon.ribbonView
import com.skydoves.transformationlayout.TransformationAppCompatActivity
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : TransformationAppCompatActivity() {

    private var _binding: ActivityDetailsBinding?= null
    private val binding get() = _binding!!
    private val pokemonDetailViewMode by viewModels<DetailViewModel>()

    // Create a HashMap for pokemonValue
    val pokemonValue = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_details)

        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("CheckingResponseDetails","Detail actiivty entered ")
        intent.getParcelableExtra<MappedData>(pokemonName)?.let {
//            Glide.with(this)
//                .load(it.imageUrl)
//                .into(binding.image)
            Log.d("CheckingResponseDetails",it.name+" "+it.detailUrl+" "+it.imageUrl)
            binding.name.text = it.name

            Glide.with(this).load(it.imageUrl).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("TAG","Image not working");
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    Palette.from(resource!!.toBitmap()).generate(){ palette->
                        palette?.let {
                            val rgb=it.dominantSwatch?.rgb
                            if (rgb != null) {
//                                binding.header.setCardBackgroundColor(rgb)
//                                binding.header.setStrokeColorResource(rgb)
                                binding.header.setBackgroundColor(rgb)
                            }
                        }
                    }
                    return false

                }

            }).into(binding.image)




            pokemonDetailViewMode.getPokemonDetail(it.detailUrl)
        }
        bindObserver()
    }

    private fun bindObserver() {
        pokemonDetailViewMode.pokemonDetailData.observe(this, Observer {it->

            binding.progressbar.isVisible=false

            when(it){
                is NetworkResult.Error -> {
                    Toast.makeText(this,"something went wrong ",Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressbar.isVisible= true
                }
                is NetworkResult.Success -> {
                    bindScreenData(it.data)
                }
            }
        })
    }

    private fun bindScreenData(pokemonData: PokemonInfoModel?) {

        binding.apply {

            //  for pokemon type name
            val type  = pokemonData!!.types

            for (pokemonType in type){
                with(ribbonRecyclerView) {
                    addRibbon(
                        ribbonView(context) {
                            setText(pokemonType.type.name)
                            setTextColor(android.graphics.Color.WHITE)
                            setPaddingLeft(84f)
                            setPaddingRight(84f)
                            setPaddingTop(2f)
                            setPaddingBottom(10f)
                            setTextSize(16f)
                            setRibbonRadius(120f)
                            setTextStyle(android.graphics.Typeface.BOLD)
                            setRibbonBackgroundColorResource(
                                BaseUtils.getTypeColor(pokemonType.type.name),
                            )
                        }.apply {
                            maxLines = 1
                            gravity = android.view.Gravity.CENTER
                        },
                    )
                    addItemDecoration(SpacesItemDecoration())
                }
            }

            val stats = pokemonData!!.stats

            for(value in stats){
                pokemonValue[value.stat.name] = value.base_stat.toString()
            }

            progressHp.progress = pokemonValue["hp"]?.toFloat()?:0f
            attackProgress.progress=pokemonValue["attack"]?.toFloat()?:0f
            progressDiff.progress = pokemonValue["defense"]?.toFloat()?:0f
            progressSpeed.progress = pokemonValue["speed"]?.toFloat()?:0f
//            progressExp.progress = pokemonValue["experienc"]?.toFloat()?:0f

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
            intent.putExtra(pokemonName,pokemon)
            TransformationCompat.startActivity(transformationLayout,intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding=null
    }

}