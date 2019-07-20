package com.example.fakenews.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class QueriedViewModelFactory (private val keyword: String?,
                               private val language: String?,
                               private val sortBy: String?):ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return QueriedViewModel(keyword, language, sortBy) as T
    }
}
