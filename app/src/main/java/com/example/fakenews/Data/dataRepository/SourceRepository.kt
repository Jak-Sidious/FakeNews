package com.example.fakenews.data.dataRepository

import androidx.lifecycle.MutableLiveData
import com.example.fakenews.BuildConfig
import com.example.fakenews.data.api.ApiClient
import com.example.fakenews.data.models.SourceX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.await
import timber.log.Timber

@Suppress("EmptyDefaultConstructor", "TooGenericExceptionCaught")
class SourceRepository()  {
    private var sourced = mutableListOf<SourceX>()
    private var mutableLiveData = MutableLiveData<List<SourceX>>()

    private val getSources by lazy {
        ApiClient.getService()
    }


    fun getMutableLiveData(): MutableLiveData<List<SourceX>> {
        CoroutineScope(Dispatchers.IO).launch {
            val request = getSources.getAllSources( "en", BuildConfig.apiKey)
            Timber.d("I was called on request $request")
            withContext(Dispatchers.Main){
                try {
                    val response = request.await()
                    sourced = response.sources
                    mutableLiveData.value = sourced
                } catch (e: HttpException) {
                    Timber.d("Sources HttpException $e")

                } catch (e: Throwable) {
                    Timber.d("From sources throwable $e")
                }
            }
        }
        return mutableLiveData
    }

}
