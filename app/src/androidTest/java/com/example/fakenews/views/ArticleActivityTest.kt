package com.example.fakenews.views


import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.fakenews.R
import com.example.fakenews.data.models.Article
import com.example.fakenews.data.models.SourceXX
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber


@LargeTest
@RunWith(AndroidJUnit4::class)
class ArticleActivityTest {
    @Rule
    @JvmField
    var rule = ActivityTestRule(ArticleActivity::class.java,
        false, false) //for article

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mArticle: ArticleActivity
    lateinit var i: Intent
    lateinit var article1: Article
    lateinit var article2: Article
    lateinit var xx1: SourceXX
    lateinit var xx2: SourceXX
    lateinit var input: List<Article>


    @Before
    fun setup() {
        i = Intent()
        i.putExtra("SourceId", "id")
        i.putExtra("SourceName", "name")
        mArticle = rule.launchActivity(i)
        mArticle.aRecyclerView
        xx1 = SourceXX("test1", "test1")
        xx2 = SourceXX("test2", "test2")
        article1 = Article(xx1, "a", "b", "c", "d", "e", "f", "g")
        article2 = Article(xx2, "h", "i", "j", "k", "l", "m", "n")
        input = listOf(article1, article2)

    }


    @Test
    fun testArticleLaunch() {
        var view = mArticle.findViewById<TextView>(R.id.Article_header)
        assertNotNull(view)
    }

    @Test
    fun testRecievedIntents() {
        var header = mArticle.viewHeader
        assertNotNull(header)
        var sourced = mArticle.sourced
        assertNotNull(sourced)
        var queryString = mArticle.queryString
        assertNull(queryString)
    }

    @Test
    fun testRecyclerView() {
        var view = mArticle.findViewById<RecyclerView>(R.id.articleRecyclerView)
        assertNotNull(view)
    }
    @Test
    fun testSetHeaderText() {
        var setter = mArticle.setHeaderText().toString()
        assertNotNull(setter)
    }

    @Test
    fun testPrepareArticlesView() {
        assertNotNull(mArticle.runOnUiThread { mArticle.getAllArticles() })
    }

    @After
    fun tearDown(){
        mArticle.finish()
    }
}

