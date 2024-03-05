package com.example.pokedex.models.details

import com.google.gson.annotations.SerializedName

data class GenerationVi(
//    @SerializedName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphire,

    @SerializedName("x-y")
    val xy: XY
)