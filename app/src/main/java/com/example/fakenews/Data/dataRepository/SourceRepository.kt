package com.example.fakenews.data.dataRepository

import android.util.Log
import androidx.annotation.VisibleForTesting
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

    //hashmap for testing
    var sourceData: LinkedHashMap<String, SourceX> = LinkedHashMap()

    private val getSources by lazy {
        ApiClient.getService()
    }


    fun getMutableLiveData(): MutableLiveData<List<SourceX>> {

        CoroutineScope(Dispatchers.IO).launch {
//            Timber.d("I was called")
            val request = getSources.getAllSources(BuildConfig.apiKey)
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

    @VisibleForTesting
    fun addSource(vararg sources: SourceX){
        for (source in sources){
            sourceData[source.name] = source
        }
    }
}

//Original
//fun refreshTitle(onStateChanged: TitleStateListener) {
//    onStateChanged(Loading)
//    val call = network.fetchNewWelcome()
//    call.addOnResultListener { result ->
//        when (result) {
//            is FakeNetworkSuccess<String> -> {
//                BACKGROUND.submit {
//                    // run insertTitle on a background thread
//                    titleDao.insertTitle(Title(result.data))
//                }
//                onStateChanged(Success)
//            }
//            is FakeNetworkError -> {
//                onStateChanged(Error(TitleRefreshError(result.error)))
//            }
//        }
//    }
//}
