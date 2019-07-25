package com.example.fakenews.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fakenews.data.dataRepository.SourceRepository

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SourceViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var sourceViewModel: SourceViewModel //subject under test
    private lateinit var sourceRepository: SourceRepository //Main repository to be injected into the view model

    @Before
    fun setUp() {
        sourceRepository = SourceRepository()
        sourceViewModel = SourceViewModel()
    }


    @Test
    fun getSourceRepository() {
        assertThat(sourceRepository).isInstanceOf(SourceRepository::class.java)
        assertThat(sourceRepository).isNotNull()
    }

    @Test
    fun getSourceViewModel(){
        assertThat(sourceViewModel).isInstanceOf(SourceViewModel::class.java)
    }

    @Test
    fun getSourceRepoFromViewModel(){
        assertThat(sourceViewModel.sourceRepository).isNotNull()
    }


    @Test
    fun getArticlesSuccesfully(){
        runBlocking {
            sourceViewModel.allSources.observeForever {
                val response =sourceRepository.getMutableLiveData()
                assertThat(response).isNotNull()
            }
        }
    }

}
