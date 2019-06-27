package com.example.fakenews.views

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakenews.R
import com.example.fakenews.adapters.ArticleAdapter
import com.example.fakenews.data.models.Article
import com.example.fakenews.viewModels.ArticleViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.article_activity.*

class ArticleActivity : AppCompatActivity() {

    var mRecyclerView: RecyclerView? = null
    var articleViewModel: ArticleViewModel? = null

    var mArticleAdapter: ArticleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_activity)
        mRecyclerView = articleRecyclerView

        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        getAllArticles()
    }

    fun getAllArticles() {
        articleViewModel!!.allArticles.observe(this, Observer {articleList ->
            prepareArticlesView(articleList)
        })
    }

    private fun prepareArticlesView(articleList: List<Article>?) {
        mArticleAdapter = ArticleAdapter(articleList)

        if(this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        } else {
            mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        }
        mRecyclerView!!.itemAnimator = DefaultItemAnimator()
        mRecyclerView!!.adapter = mArticleAdapter

    }
}
