package com.example.pokedex.ui.main

import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex.databinding.LayoutPokemonCardBinding
import com.example.pokedex.models.MappedData
import com.example.pokedex.ui.details.DetailsActivity

class PokemonListAdapter():ListAdapter<MappedData, PokemonListAdapter.PokemonViewHolder>(
    ComparatorDiffUtil()
) {

    private var onClickedAt=0L

    inner class PokemonViewHolder(private val binding: LayoutPokemonCardBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(pokemon: MappedData){
                binding.apply {
//                    pokemonId.text = "#+${formatNumber(pokemon.id.toInt())}";
                    name.text = pokemon.name
//                    Glide.with(binding.root).load(pokemon.imageUrl).into(image)

                    Glide.with(root.context).load(pokemon.imageUrl).listener(object :RequestListener<Drawable>{
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
                            Palette.from(resource!!.toBitmap()).generate(){palette->
                                palette?.let {
                                    val rgb=it.dominantSwatch?.rgb
                                    if (rgb != null) {
                                        cardView.setCardBackgroundColor(rgb)
                                    }
                                }
                            }
                            return false

                        }

                    }).into(image)

//                    Glide.with(root.context)
//                        .load(pokemon.imageUrl)
//                        .listener(
//                            GlidePalette.with(pokemon.imageUrl)
//                                .use(BitmapPalette.Profile.MUTED_LIGHT)
//                                .intoCallBack { palette ->
//                                    val rgb = palette?.dominantSwatch?.rgb
//                                    if (rgb != null) {
//                                        cardView.setCardBackgroundColor(rgb)
//                                    }
//                                }.crossfade(true),
//                        ).into()

                    root.setOnClickListener(){
                        val currentClickedAt = SystemClock.elapsedRealtime()
                        if (currentClickedAt - onClickedAt > transformationLayout.duration) {
                            DetailsActivity.startActivity(root.context,transformationLayout,pokemon)
                        }
                    }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
            val binding = LayoutPokemonCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        pokemon?.let {
            holder.bind(it)
        }
    }



    class ComparatorDiffUtil : DiffUtil.ItemCallback<MappedData>() {
        override fun areItemsTheSame(oldItem: MappedData, newItem: MappedData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MappedData, newItem: MappedData): Boolean {
            return oldItem == newItem
        }
    }

    fun formatNumber(number: Int): String {
        return when {
            number in 0..9 -> String.format("%03d", number)
            else -> number.toString()
        }
    }


}