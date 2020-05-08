package com.spaceo.practicaltest

import com.spaceo.practicaltest.Practical1.Model.ResponseData
import retrofit2.Call
import retrofit2.http.GET


//https://github.com/probelalkhan/kotlin-retrofit-tutorial


interface Api {
    @GET("demo_api.json")
    fun getDatas():Call<ResponseData>








}