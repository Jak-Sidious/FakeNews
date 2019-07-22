package com.example.fakenews.views


import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.fakenews.R
import com.example.fakenews.data.models.Article
import com.example.fakenews.data.models.SourceXX
import com.example.fakenews.viewModels.ArticleViewModel
import com.example.fakenews.viewModels.QueriedViewModel
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


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
    lateinit var mArticle1: ArticleActivity
    lateinit var i: Intent
    lateinit var sent: Bundle
    lateinit var article1: Article
    lateinit var article2: Article
    lateinit var xx1: SourceXX
    lateinit var xx2: SourceXX
    lateinit var input: List<Article>
    lateinit var aVm: ArticleViewModel
    lateinit var qVm: QueriedViewModel


    @Before
    fun setup() {
        i = Intent()
        i.putExtra("SourceId", "id")
        i.putExtra("SourceName", "name")
        i.putExtra("SearchQuery", "query")
        mArticle = rule.launchActivity(i)
        sent = mArticle.sent
        mArticle.aRecyclerView
        xx1 = SourceXX("test1", "test1")
        xx2 = SourceXX("test2", "test2")
        article1 = Article(xx1, "a", "b", "c", "d", "e", "f", "g")
        article2 = Article(xx2, "h", "i", "j", "k", "l", "m", "n")
        input = listOf(article1, article2)
        mArticle1 = rule.activity
        mArticle1.aRecyclerView
        mArticle1.aArticleAdapter

        aVm = mArticle1.articleViewModel!!
        qVm = mArticle1.queryViewModel!!
    }


    @Test
    fun testArticleActivityLaunch() {
        var view = mArticle.findViewById<TextView>(R.id.Article_header)
        assertNotNull(view)
    }

    @Test
    fun testViewModels(){
        assertEquals(aVm, mArticle.articleViewModel)
        assertEquals(qVm, mArticle.queryViewModel)
    }

    @Test
    fun testRecievedIntents() {
        var header = mArticle.viewHeader
        assertNotNull(header)
        var sourced = mArticle.sourced
        assertNotNull(sourced)
        var queryString = mArticle.queryString
        assertNotNull(queryString)
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
    fun testgetAllArticles() {
        assertNotNull(mArticle.runOnUiThread { mArticle.getAllArticles() })
    }


    @After
    fun tearDown(){
        mArticle.finish()
    }
}

