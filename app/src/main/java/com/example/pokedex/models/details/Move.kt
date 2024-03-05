package com.example.pokedex.models.details

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)