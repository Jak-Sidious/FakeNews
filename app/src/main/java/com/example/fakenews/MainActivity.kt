package com.example.fakenews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fakenews.Data.api.NewsApiService
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun provideRetrofitService() : Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .build()
    }

    fun getApiService() {
       val newsService: NewsApiService = provideRetrofitService().create(NewsApiService::class.java)
    }
}
