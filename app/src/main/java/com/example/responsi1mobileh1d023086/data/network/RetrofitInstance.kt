package com.example.responsi1mobileh1d023086.data.network

import com.example.responsi1mobileh1d023086.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-Auth-Token", "feaab1988df44f03827076ebc391bbf2")
                .build()
            chain.proceed(request)
        }
        .build()

    val api: FootballDataApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(FootballDataApi::class.java)
    }
}