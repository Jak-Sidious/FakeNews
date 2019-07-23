package com.example.fakenews.views


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.fakenews.R
import com.example.fakenews.adapters.ArticleAdapter
import com.example.fakenews.data.models.Article
import com.example.fakenews.data.models.ArticlesPerSource
import com.example.fakenews.data.models.SourceXX
import com.example.fakenews.utils.ClickListener
import com.example.fakenews.utils.RecyclerTouchListener
import com.example.fakenews.viewModels.ArticleViewModel
import com.example.fakenews.viewModels.QueriedViewModel
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
    lateinit var i: Intent
    lateinit var sent: Bundle
    lateinit var article1: Article
    lateinit var article2: Article
    lateinit var xx1: SourceXX
    lateinit var xx2: SourceXX
    lateinit var aPs: ArticlesPerSource
    lateinit var input: List<Article>
    lateinit var input2: MutableList<Article>
    lateinit var aVm: ArticleViewModel
    lateinit var qVm: QueriedViewModel
    lateinit var aSwipeRefreshLayout: SwipeRefreshLayout

    @Before
    fun setup() {
        i = Intent()
        i.putExtra("SourceId", "id")
        i.putExtra("SourceName", "name")
        i.putExtra("SearchQuery", "query")
        mArticle = rule.launchActivity(i)
        sent = mArticle.sent
        mArticle.aRecyclerView
        aSwipeRefreshLayout = mArticle.aSwipeRefresh!!
        xx1 = SourceXX("test1", "test1")
        xx2 = SourceXX("test2", "test2")
        article1 = Article(xx1, "a", "b", "c", "d", "e", "f", "g")
        article2 = Article(xx2, "h", "i", "j", "k", "l", "m", "n")
        input = listOf(article1, article2)
        input2 = mutableListOf(article1,article2)
        aPs = ArticlesPerSource("OK", 5000, input2)
        sent = mArticle.sent
        aVm = mArticle.articleViewModel!!
        qVm = mArticle.queryViewModel!!
    }

    @Test
    fun testArticlesPerSource() {
        assertNotNull(aPs.status)
        assertNotNull(aPs.totalResults)
        assertEquals(aPs.totalResults, 5000)
        assertNotNull(aPs.copy("no", 20, input2))
        assertEquals(aPs,ArticlesPerSource("OK", 5000, input2))
        assertNotNull(aPs.toString())
        assertNotNull(aPs.articles)
        assertEquals(input.size, 2)
    }

    @Test
    fun testAdapterGetString(){
        mArticle.aArticleAdapter = ArticleAdapter(input)
        assertNotNull(mArticle.aArticleAdapter.toString())
    }

    @Test
    fun testSourceXXgetters() {
        assertNotNull(xx1.id)
        assertNotNull(xx1.name)
        assertNotNull(xx1.copy("blah","blah blah"))
        assertNotEquals(xx1, xx2)
        assertNotEquals(article1,article2)
        assertNotNull(article1.toString())
        assertNotNull(article1.content)
        assertNotNull(article2.url)
        assertNotNull(article1.copy(xx2,"me", "me","me","you","you",
            "me","sagjdjsafdjsfadjsa"))
        assertEquals(article1, article1)
    }

    @Test
    fun testArticleActivityLaunch() {
        var view = mArticle.findViewById<TextView>(R.id.Article_header)
        assertNotNull(view)
    }

    @Test
    fun testBundle(){
        assertNotNull(sent)
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
    fun testSwipeRefresh() {
        var refresher = mArticle.findViewById<SwipeRefreshLayout>(R.id.swipeRefresher)
        assertNotNull(refresher)
        assertNotNull(aSwipeRefreshLayout)
        assertNotNull(aSwipeRefreshLayout.setOnRefreshListener { mArticle.getAllArticles() })
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

    @Test
    fun testPrepareArticleList() {
        assertNotNull(mArticle.runOnUiThread { mArticle.prepareArticlesView(input) })
    }

    @Test @Config(qualifiers = "land")
    fun testPrepareArticleListLandscape(){
        assertNotNull(mArticle.runOnUiThread { mArticle.prepareArticlesView(input) })
//        assert(mArticle..resources.configuration.equals(Configuration.ORIENTATION_PORTRAIT))
        assert(mArticle.aRecyclerView!!.resources.configuration.equals(Configuration.ORIENTATION_LANDSCAPE))
    }


    @Test
    fun testRecyclerViewOnClickLaunchesWebView() {
        mArticle.setVisible(true)
        var recycler = mArticle.runOnUiThread { mArticle.prepareArticlesView(input) }
        assertNotNull(recycler)
        var recycled = mArticle.findViewById<RecyclerView>(R.id.articleRecyclerView)
        recycled.measure(0,0)
        recycled.layout(0,0, 100, 100)
        var clicker = mArticle.runOnUiThread {   recycled.addOnItemTouchListener(
            RecyclerTouchListener(mArticle.applicationContext, recycled, object: ClickListener {
                override fun onClick(view: View, position: Int) {
                    val mainToast = Toast.makeText(mArticle.applicationContext, input[0].url,
                        Toast.LENGTH_SHORT)
                    assertNotNull(mainToast)
                    mainToast.setGravity(Gravity.CENTER, 0 ,0)
                    mainToast.show()
                    assertNotNull(mainToast.show())
                    val i = Intent(mArticle.applicationContext, ArticleDisplayActivity::class.java)
                    i.putExtra("ArticleUrl", input[0].url)
                    assertNotNull(mArticle.startActivity(i))
                }

            })
        )}
        assertNotNull(clicker)
        recycled.findViewHolderForAdapterPosition(0)?.itemView?.performClick()?.let { assertTrue(it) }
    }

    @Test
    fun testgetAllArticles2(){
        mArticle.finish()
        var ext = Intent()
        ext.putExtra("SourceId", "id")
        ext.putExtra("SourceName", "name")
        mArticle = rule.launchActivity(ext)
        var header = mArticle.viewHeader
        assertNotNull(header)
        assertNotNull(input)
        assertNotNull(mArticle.runOnUiThread { mArticle.prepareArticlesView(input) })
    }

    @After
    fun tearDown(){
        mArticle.finish()
    }
}

