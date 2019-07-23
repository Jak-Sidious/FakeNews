package com.example.fakenews.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fakenews.data.dataRepository.ArticlesPerSourceRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class QueriedViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var queryViewModel: QueriedViewModel
    private lateinit var queryRepository:  ArticlesPerSourceRepository

    @Before
    fun setUp() {
        queryRepository = ArticlesPerSourceRepository()
        queryViewModel = QueriedViewModel("query", "en", "published")
    }

    @Test
    fun getQueryRepository(){
        assertThat(queryRepository).isInstanceOf(ArticlesPerSourceRepository::class.java)
    }

    @Test
    fun getQueryViewModel() {
        assertThat(queryViewModel).isInstanceOf(QueriedViewModel::class.java)
    }

    @Test
    fun getQueriedArticles() {
        queryViewModel.queriedArticles.observeForever {}
            val response = queryRepository.getQueriedArticleData(
                "andela",
                "en", "publishedAt"
            )
            assertThat(response).isNotNull()
    }
}

