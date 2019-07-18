package com.example.fakenews.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ArticleViewModelFactory (private val source: String?): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleViewModel(source) as T
    }
}
