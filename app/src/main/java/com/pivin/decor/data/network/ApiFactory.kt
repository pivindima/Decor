package com.pivin.decor.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    const val BASE_URL = "http://92.63.105.40"
    private const val API_URL = "/api/decor/v1/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL + API_URL)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}