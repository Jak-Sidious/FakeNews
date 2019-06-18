package com.example.fakenews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fakenews.Data.api.ApiClient
import com.example.fakenews.Data.models.Source
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    var dataList = ArrayList<Source>()
    val apiKey: String = "6dff1c5c016f40d3897868869bed2449"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    private fun getData() {
        val call: Call<Source> = ApiClient.getService().getAllSources(apiKey)
        call.enqueue(object : Callback<Source>{
            override fun onResponse(call: Call<Source>, response: Response<Source>) {
                Timber.d("Response on pass${response.body()}")
            }

            override fun onFailure(call: Call<Source>, t: Throwable) {
                Timber.d("Response on failure")
            }
        })

        }
    }


