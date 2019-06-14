package com.example.fakenews.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fakenews.DataRepository.SourceRepository
import javax.inject.Inject

//TODO use a hashmap function for the images to create a better card view

class SourceViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    sourceRepository: SourceRepository
) : ViewModel(){
    val sourceID : LiveData<Sources> = sourceRepository.getSources()
//    val sourceName : LiveData<Source.name> = TODO()
//    val description : LiveData<Source.description> = TODO()
//    val url : LiveData<Source.url> = TODO()
//    val category : LiveData<Source.category> = TODO()
//    val language : LiveData<Source.language> = TODO()
//    val country : LiveData<Source.language> = TODO()
}


