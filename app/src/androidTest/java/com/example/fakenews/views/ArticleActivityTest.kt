package com.example.fakenews.views


import android.widget.TextView
import androidx.test.rule.ActivityTestRule
import com.example.fakenews.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArticleActivityTest {
    @Rule
    @JvmField
    var rule = ActivityTestRule(ArticleActivity::class.java)

    lateinit var mArticle: ArticleActivity

    @Before
    fun setup() {
        mArticle = rule.activity
    }

//    @Test
//    fun testArticleLaunch() {
//        var view = mArticle.findViewById<TextView>(R.id.Article_header)
//        assertNotNull(view)
//    }
}