package com.example.pokedex.models

import com.google.gson.annotations.SerializedName


data class FinalDataResponse(
//    @SerializedName("abilities"                ) var abilities              : ArrayList<String> = arrayListOf(),
//    @SerializedName("base_experience"          ) var baseExperience         : Int?              = null,
//    @SerializedName("forms"                    ) var forms                  : ArrayList<String> = arrayListOf(),
//    @SerializedName("game_indices"             ) var gameIndices            : ArrayList<String> = arrayListOf(),
//    @SerializedName("height"                   ) var height                 : Int?              = null,
//    @SerializedName("held_items"               ) var heldItems              : ArrayList<String> = arrayListOf(),
    @SerializedName("id") var id: Int? = null,
    @SerializedName("is_default") var isDefault: Boolean? = null,
    @SerializedName("location_area_encounters") var locationAreaEncounters: String? = null,
//    @SerializedName("moves"                    ) var moves                  : ArrayList<String> = arrayListOf(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("order") var order: Int? = null,
//    @SerializedName("past_abilities"           ) var pastAbilities          : ArrayList<String> = arrayListOf(),
//    @SerializedName("past_types"               ) var pastTypes              : ArrayList<String> = arrayListOf(),
//    @SerializedName("stats"                    ) var stats                  : ArrayList<String> = arrayListOf(),
//    @SerializedName("types"                    ) var types                  : ArrayList<String> = arrayListOf(),
    @SerializedName("sprites") var sprites: Sprites? = null,
    @SerializedName("weight") var weight: Int? = null,
    @SerializedName("types") var type: ArrayList<Types>? = null

)

data class Sprites(
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)

data class Types(
    val slot: Int?,
    val type: Type
)

data class Type(
    val name: String?,
    val url: String?
)
