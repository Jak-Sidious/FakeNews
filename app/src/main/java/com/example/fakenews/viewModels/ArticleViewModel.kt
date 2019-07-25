package com.example.fakenews.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fakenews.data.dataRepository.ArticlesPerSourceRepository
import com.example.fakenews.data.models.Article


class ArticleViewModel(val sourceString: String?) : ViewModel(){
    val articleRepository = ArticlesPerSourceRepository()
    val allArticles: LiveData<List<Article>> get() = articleRepository.getMutableArticleData(source =sourceString)
}
