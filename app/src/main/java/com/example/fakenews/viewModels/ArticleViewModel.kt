package com.example.fakenews.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fakenews.data.dataRepository.ArticlesPerSourceRepository
import com.example.fakenews.data.models.Article

class ArticleViewModel (application: Application) : AndroidViewModel(application){

    val articleRepository = ArticlesPerSourceRepository()
    val allArticles: LiveData<List<Article>> get() = articleRepository.getMutableArticleData()
}
