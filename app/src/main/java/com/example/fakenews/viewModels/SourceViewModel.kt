package com.example.fakenews.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.fakenews.Data.dataRepository.SourceRepository
import com.example.fakenews.Data.models.SourceX

class SourceViewModel(application: Application): AndroidViewModel(application) {

    val sourceRepository= SourceRepository()
    val allSources: LiveData<List<SourceX>> get() = sourceRepository.getMutableLiveData()

}


