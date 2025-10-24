package com.example.responsi1mobileh1d023065.data.model

import com.google.gson.annotations.SerializedName

data class HeadCoach(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("nationality")
    val nationality: String,
)

data class HeadCoachResponse(
    @SerializedName("coach")
    val coach: HeadCoach
)