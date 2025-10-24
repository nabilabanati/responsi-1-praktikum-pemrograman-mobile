package com.example.responsi1mobileh1d023065.data.model

import com.google.gson.annotations.SerializedName

data class TeamSquad(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("nationality")
    val nationality: String,
)

data class TeamSquadResponse(
    @SerializedName("squad")
    val squad: List<TeamSquad>,
)