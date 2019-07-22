package com.example.fakenews.views

import android.content.Intent
import android.webkit.WebView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.fakenews.R
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDisplayActivityTest {
    @Rule
    @JvmField
    var rule = ActivityTestRule(ArticleDisplayActivity::class.java,
        false, false)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var i: Intent
    lateinit var aADisplay: ArticleDisplayActivity

    @Before
    fun setup() {
        i = Intent()
        i.putExtra("ArticleUrl", "https://www.tickles.com")
        aADisplay = rule.launchActivity(i)

    }

    @Test
    fun testWebView(){
        var webView = aADisplay.findViewById<WebView>(R.id.article_viewer)
        assertNotNull(webView)
    }

    @After
    fun tearDown() {
        aADisplay.finish()
    }
}