package com.spaceo.practicaltest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient  {
    private const val BASE_URL_PRACTICAL1 = "https://dl.dropboxusercontent.com/s/p57gxwqm84zkp96/"

    val instance: Api by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_PRACTICAL1)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(Api::class.java)
    }
}