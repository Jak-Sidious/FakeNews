package com.example.fakenews.views

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.GestureDetector
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fakenews.R
import com.example.fakenews.adapters.ArticleAdapter
import com.example.fakenews.data.models.Article
import com.example.fakenews.utils.ClickListener
import com.example.fakenews.utils.RecyclerTouchListener
import com.example.fakenews.viewModels.ArticleViewModel
import com.example.fakenews.viewModels.ArticleViewModelFactory
import com.example.fakenews.viewModels.QueriedViewModel
import com.example.fakenews.viewModels.QueriedViewModelFactory
import kotlinx.android.synthetic.main.article_activity.*
import timber.log.Timber

class ArticleActivity : AppCompatActivity() {

    var aRecyclerView: RecyclerView? = null
    var articleViewModel: ArticleViewModel? = null
    var queryViewModel: QueriedViewModel? = null
    var aArticleAdapter: ArticleAdapter? = null
    var aSwipeRefresh: SwipeRefreshLayout? = null
    lateinit var sent : Bundle
    var viewHeader: String? = null
    var sourced: String? = null
    var queryString: String? = null
    var articleState: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_activity)

        if (savedInstanceState != null){
            with(savedInstanceState){
                articleState = getString(ARTICLE_STATE_KEY)
                viewHeader = getString(VH_VIEW_KEY)
                sourced = getString(SOURCED_VIEW_KEY)
                queryString = getString(QUERY_VIEW_KEY)
            }
        } else {
            sent = intent.extras
            viewHeader = sent.getString("SourceName")
            sourced = sent.getString("SourceId")
            queryString = sent.getString("SearchQuery")
        }

        articleState = savedInstanceState?.getString(ARTICLE_STATE_KEY)
        aSwipeRefresh = swipeRefresher
        aRecyclerView = articleRecyclerView
        createArticleView()
        articleViewModel = ViewModelProviders.of(
            this, ArticleViewModelFactory(sourced)).get(ArticleViewModel::class.java)
        queryViewModel = ViewModelProviders.of(
            this, QueriedViewModelFactory(queryString,"en","publishedAt")).get(QueriedViewModel::class.java)
        getAllArticles()
        aSwipeRefresh?.setOnRefreshListener {
            getAllArticles()
        }
        aSwipeRefresh?.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)
        setHeaderText()
        onBackPressed()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        viewHeader = savedInstanceState?.getString(VH_VIEW_KEY)
        sourced = savedInstanceState?.getString(SOURCED_VIEW_KEY)
        queryString = savedInstanceState?.getString(QUERY_VIEW_KEY)

        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putString(ARTICLE_STATE_KEY, articleState)
            putString(VH_VIEW_KEY, viewHeader)
            putString(SOURCED_VIEW_KEY, sourced)
            putString(QUERY_VIEW_KEY, queryString)
        }

        super.onSaveInstanceState(outState)
    }

    companion object {
        val ARTICLE_STATE_KEY = "ArticleState"
        val VH_VIEW_KEY = "ViewHeader"
        val SOURCED_VIEW_KEY = "SourcedString"
        val QUERY_VIEW_KEY = "QueryString"
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    fun createArticleView(): String? {
        return if(viewHeader == null){
            queryString
        } else {
            viewHeader
        }
    }

    fun setHeaderText(){
        if ( viewHeader == null) {
            Article_header.text = queryString
        } else {
            Article_header.text = viewHeader
        }
    }

    fun getAllArticles() {
        aSwipeRefresh!!.isRefreshing = false
        if (queryString == null){
            articleViewModel!!.allArticles.observe(this, Observer { articleList ->
                prepareArticlesView(articleList)
            })
        } else {
            queryViewModel!!.queriedArticles.observe(this, Observer { queryList ->
                prepareArticlesView(queryList)
            })
        }
    }

    fun prepareArticlesView(articleList: List<Article>?) {
        aArticleAdapter = ArticleAdapter(articleList)
        if(this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            aRecyclerView!!.layoutManager = LinearLayoutManager(this)
        } else {
            aRecyclerView!!.layoutManager = LinearLayoutManager(this)
        }
        aRecyclerView!!.itemAnimator = DefaultItemAnimator()
        aRecyclerView!!.adapter = aArticleAdapter
        aRecyclerView!!.addOnItemTouchListener(
            RecyclerTouchListener(this, aRecyclerView!!, object : ClickListener {
                    override fun onClick(view: View, position: Int) {
                        val articleToast = Toast.makeText(this@ArticleActivity,
                            articleList!![position].url, Toast.LENGTH_SHORT)
                        articleToast.setGravity(Gravity.CENTER, 0 ,0)
                        articleToast.show()
                        val webs = Intent(this@ArticleActivity, ArticleDisplayActivity::class.java)
                        webs.putExtra("ArticleUrl", articleList[position].url)
                        startActivity(webs)
                    }
                })
        )
    }
}
