package com.example.fakenews.data.api


import com.example.fakenews.data.models.ArticlesPerSource
import com.example.fakenews.data.models.Source
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("sources")
    fun getAllSources(
        @Query("language") language: String?,
        @Query("apiKey")apiKey: String): Call<Source>

    @GET("everything")
    fun getArticlesPerSource(
        @Query("sources") sourceName: String?,
        @Query("language") language: String?,
        @Query("apikey") apiKey: String
    ): Call<ArticlesPerSource>

    @GET("everything")
    fun getSearchQuery(
        @Query("q") keyword: String?,
        @Query("language") language: String?,
        @Query("sortBy") sortBy: String?,
        @Query("apiKey") apiKey: String
    ): Call<ArticlesPerSource>
}
