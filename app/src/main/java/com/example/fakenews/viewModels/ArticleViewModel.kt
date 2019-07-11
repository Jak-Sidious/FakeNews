package com.example.fakenews.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fakenews.data.dataRepository.ArticlesPerSourceRepository
import com.example.fakenews.data.models.Article

@Suppress("EmptyDefaultConstructor")
class ArticleViewModel () : ViewModel(){

    val articleRepository = ArticlesPerSourceRepository()
    val allArticles: LiveData<List<Article>> get() = articleRepository.getMutableArticleData()
}
