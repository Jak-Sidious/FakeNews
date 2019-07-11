package com.example.fakenews.viewModels


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fakenews.data.dataRepository.SourceRepository
import com.example.fakenews.data.models.SourceX
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SourceViewModelTest {

    //subject under test
    private lateinit var sourceViewModel: SourceViewModel

    //Main repository to be injected into the view model
    private lateinit var sourceRepository: SourceRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //Testing Source
    val source = SourceX("test1", "Test source", "The source for testing",
    "https://yasss.com", "Testing stuff", "english", "Kenya")

    @Before
    fun setUp() {
        sourceRepository = SourceRepository()
//
        sourceViewModel = SourceViewModel()
    }

    @Test
    fun getSourceRepository() {
        assertThat(sourceRepository).isInstanceOf(SourceRepository::class.java)
    }

    @Test
    fun getAllSources() {
        sourceRepository.addSource(source)
        assertThat(sourceRepository.sourceData.size).isEqualTo(1)
    }
}