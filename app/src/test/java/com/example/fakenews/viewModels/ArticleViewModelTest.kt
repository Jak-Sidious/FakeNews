package com.example.fakenews.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fakenews.data.dataRepository.ArticlesPerSourceRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ArticleViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var articleRepository: ArticlesPerSourceRepository

    @Before
    fun setUp() {
        articleRepository = ArticlesPerSourceRepository()
        articleViewModel = ArticleViewModel("source")
    }

    @Test
    fun getArticleRepository(){
        assertThat(articleRepository).isInstanceOf(ArticlesPerSourceRepository::class.java)
    }

    @Test
    fun getArticleViewModel(){
        assertThat(articleViewModel).isInstanceOf(ArticleViewModelFactory::class.java)
    }

    @Test
    fun getAllArticlesSuccesfully() {
        runBlocking {
            articleViewModel.allArticles.observeForever {  }
            val response = articleRepository.getMutableArticleData("abc")
            assertThat(response).isNotNull()
        }
    }
}