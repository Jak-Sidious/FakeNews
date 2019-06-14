package com.example.fakenews.DataRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fakenews.Data.api.NewsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SourceRepository @Inject constructor(
    private val newsApiService: NewsApiService
) {
    private val newsApiService: NewsApiService = TODO()

    fun getSources(): LiveData<Sources> {
        val data = MutableLiveData<Sources>()
        newsApiService.getSources().enqueue(object : Callback<Sources> {
            override fun onResponse(call: Call<Sources>, response: Response<Sources>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<Sources>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        return data
    }
}