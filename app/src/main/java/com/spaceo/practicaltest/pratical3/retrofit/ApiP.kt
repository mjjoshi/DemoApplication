package com.spaceo.practicaltest.pratical3.retrofit

import com.google.gson.JsonObject
import com.spaceo.practicaltest.pratical3.ResponsePractical
import retrofit2.Call
import retrofit2.http.*


//https://github.com/probelalkhan/kotlin-retrofit-tutorial



interface ApiP {
    @Headers("issecuritydisable:0")
    @POST("merchant")
    fun getDatas(@Query("params")objects: JsonObject): Call<ResponsePractical>
}