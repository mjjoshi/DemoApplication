package com.spaceo.practicaltest.pratical3.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




object RetrofitClientP {
    private const val BASE_URL_PRACTICAL = "https://staging.sunteccity.com.sg/ver16/index.php/apitokenv4/list/model/"
    val INSTANCE: ApiP by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_PRACTICAL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiP::class.java)
    }
}