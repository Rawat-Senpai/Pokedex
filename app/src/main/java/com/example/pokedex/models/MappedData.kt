package com.example.pokedex.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MappedData(
    val name:String,
    val imageUrl:String,
    val id:String,
    val detailUrl:String,

) : Parcelable