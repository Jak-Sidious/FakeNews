package com.example.fakenews.views

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
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
import kotlinx.android.synthetic.main.article_activity.*

class ArticleActivity : AppCompatActivity() {

    var aRecyclerView: RecyclerView? = null
    var articleViewModel: ArticleViewModel? = null
    var aArticleAdapter: ArticleAdapter? = null
    var viewHeader: String? = null
    var queryString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_activity)
        var sent: Bundle = intent.extras
        viewHeader = sent.getString("SourceName")
        queryString = sent.getString("SearchQuery")
        aRecyclerView = articleRecyclerView
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        getAllArticles()
        setHeaderText()
    }

    fun setHeaderText(){
        if ( viewHeader == null) {
            Article_header.text = queryString
        } else {
            Article_header.text = viewHeader
        }
    }

    fun getAllArticles() {
        articleViewModel!!.allArticles.observe(this, Observer { articleList ->
            prepareArticlesView(articleList)
        })

    }

    private fun prepareArticlesView(articleList: List<Article>?) {
        aArticleAdapter = ArticleAdapter(articleList)

        if(this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            aRecyclerView!!.layoutManager = LinearLayoutManager(this)
        } else {
            aRecyclerView!!.layoutManager = LinearLayoutManager(this)
        }
        aRecyclerView!!.itemAnimator = DefaultItemAnimator()
        aRecyclerView!!.adapter = aArticleAdapter
        aRecyclerView!!.addOnItemTouchListener(
            RecyclerTouchListener(
                this,
                aRecyclerView!!,
                object : ClickListener {

                    override fun onClick(view: View, position: Int) {
                        Toast.makeText(this@ArticleActivity, articleList!![position].url, Toast.LENGTH_SHORT).show()
                        val webs = Intent(this@ArticleActivity, ArticleDisplayActivity::class.java)
                        webs.putExtra("ArticleUrl", articleList!![position].url)
                        startActivity(webs)
                    }
                })
        )
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)
    }

    internal class RecyclerTouchListener(
        context: Context,
        recyclerView: RecyclerView,
        private val clickListener: ClickListener?)
        : RecyclerView.OnItemTouchListener {
        private var gestureDetector: GestureDetector? = null

        init {
            gestureDetector = GestureDetector(context, object: GestureDetector.SimpleOnGestureListener(){
                override fun onSingleTapUp(e: MotionEvent?): Boolean {
                    return true
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector!!.onTouchEvent(e)){
                clickListener.onClick(child, rv.getChildPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}
