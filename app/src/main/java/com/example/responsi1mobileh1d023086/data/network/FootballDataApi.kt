package com.example.responsi1mobileh1d023086.data.network

import com.example.responsi1mobileh1d023065.data.model.ClubSquadResponse
import com.example.responsi1mobileh1d023065.data.model.HeadCoachResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballDataApi {

    @GET("{clubId}")
    suspend fun getClubSquad(@Path("clubId") clubId: Int): Response<ClubSquadResponse>

    @GET("{clubId}")
    suspend fun getHeadCoach(@Path("clubId") clubId: Int): Response<HeadCoachResponse>

}