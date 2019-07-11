package com.example.fakenews.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fakenews.data.dataRepository.SourceRepository
import com.example.fakenews.data.models.SourceX

//TODO() Refactor as ViewModels can only accept models and view models
@Suppress("EmptyDefaultConstructor")
class SourceViewModel(): ViewModel() {

    val sourceRepository= SourceRepository()
    val allSources: LiveData<List<SourceX>> get() = sourceRepository.getMutableLiveData()
}


