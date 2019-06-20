package com.example.fakenews.Data.api


import com.example.fakenews.Data.models.Source
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface apiInterface {

    @GET("sources")
    fun getAllSources(@Query("apiKey")apiKey: String): Call<Source>
}



