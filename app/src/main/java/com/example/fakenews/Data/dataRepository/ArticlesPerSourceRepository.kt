package com.example.fakenews.data.dataRepository

import androidx.lifecycle.MutableLiveData
import com.example.fakenews.BuildConfig
import com.example.fakenews.data.api.ApiClient
import com.example.fakenews.data.models.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import retrofit2.HttpException
import retrofit2.await
import timber.log.Timber

@Suppress("EmptyDefaultConstructor", "TooGenericExceptionCaught")
class ArticlesPerSourceRepository() {

    private var collected = mutableListOf<Article>()
    private var mutableArticleData = MutableLiveData<List<Article>>()
    private var requested = mutableListOf<Article>()
    private var mutableQueryData = MutableLiveData<List<Article>>()


    private val getArticles by lazy {
        ApiClient.getService()
    }

    fun getMutableArticleData(source: String?): MutableLiveData<List<Article>> {
        CoroutineScope(Dispatchers.IO).launch {
            val request = getArticles.getArticlesPerSource(source, BuildConfig.apiKey)
            withContext(Dispatchers.Default) {
                try {
                    val response = request.await()
                    collected = response.articles
                    mutableArticleData.postValue(collected)
                    Timber.d("This is it $mutableArticleData")
                } catch (e: HttpException) {
                    Timber.e("Le Articles HttpException $e")

                } catch (e: Throwable) {
                    Timber.e("Le Articles Throwable $e")
                }
            }
            Timber.d("This is it too $mutableArticleData")
        }
        return mutableArticleData
    }

    fun getQueriedArticleData(
        keyword: String?,
        language: String?,
        sortBy: String?
    ): MutableLiveData<List<Article>> {
        CoroutineScope(Dispatchers.IO).launch {
            val request = getArticles.getSearchQuery(keyword, language, sortBy, BuildConfig.apiKey)
            withContext(Dispatchers.Default) {
                try {
                    val responded = request.await()
                    requested = responded.articles
                    mutableQueryData.postValue(requested)
                    Timber.d("This was queried $mutableQueryData")
                } catch (e: HttpException) {
                    Timber.e("Queried articles HttpException $e")

                } catch (e: Throwable) {
                    Timber.e("Queried articles Throwable $e")
                }
            }
            Timber.d("This was Queried $mutableQueryData")
        }
        return mutableQueryData
    }
}
