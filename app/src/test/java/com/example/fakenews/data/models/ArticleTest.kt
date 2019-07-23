package com.example.fakenews.data.models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ArticleTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var testSource: SourceXX
    lateinit var testArticle: Article

    @Before
    fun setup(){
        testSource = SourceXX("testing", "tester")
        testArticle = Article(testSource, "jakana", "test", "testing description",
            "https://yasss.com", "https://yasss.com", "today", "sgdjagdjasgjdsgad")
    }

    @Test
    fun testTypes(){
        assertThat(testSource).isInstanceOf(SourceXX::class.java)
        assertThat(testSource).isNotNull()
        assertThat(testArticle).isInstanceOf(Article::class.java)
        assertThat(testArticle).isNotNull()
    }
}