package com.example.fakenews.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fakenews.data.dataRepository.ArticlesPerSourceRepository
import com.example.fakenews.data.models.Article

class QueriedViewModel (val keyword: String?, val language: String?, val sortBy: String?) : ViewModel(){
    val queryRepository = ArticlesPerSourceRepository()
    val queriedArticles: LiveData<List<Article>> get() = queryRepository.getQueriedArticleData(keyword=keyword,
        language = language, sortBy = sortBy)
}
