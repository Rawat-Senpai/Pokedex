package com.example.pokedex.models.details

import com.google.gson.annotations.SerializedName

data class GenerationIv(

//    @SerializedName("diamond-pearl")
    val diamondPearl: DiamondPearl,

//    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilver,

    val platinum: Platinum
)