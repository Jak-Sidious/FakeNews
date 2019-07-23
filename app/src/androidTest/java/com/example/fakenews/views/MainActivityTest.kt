package com.example.fakenews.views

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import com.example.fakenews.R
import com.example.fakenews.data.models.SourceX
import org.junit.Before
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.filters.LargeTest
import com.example.fakenews.data.models.Source
import org.junit.After


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mActivity: MainActivity
    lateinit var source1: SourceX
    lateinit var source2: SourceX
    lateinit var source: Source
    lateinit var input: List<SourceX>
    lateinit var input1: MutableList<SourceX>

    @Before
    fun setUp() {
        mActivity =  rule.activity
        mActivity.mRecyclerView
        source1 = SourceX("a","b","c", "d", "e", "f", "g")
        source2 = SourceX("h","i","j","k","l","m","n")
        input = listOf(source1, source2)
        input1 = mutableListOf(source1, source2)
        source = Source("ok",input1)
    }

    @Test
    fun testSource() {
        assertNotNull(source.status)
        assertNotNull(source.copy("ok",input1))
        assertNotNull(source.toString())
        assertNotNull(source.sources)
        source.status = "blink"
        assertEquals(source.status, "blink")
    }

    @Test
    fun testSourceX() {
        assertNotEquals(source1,source2)
    }

    @Test
    fun testSourceGetters() {
        assertNotNull(source1.id)
        assertNotNull(source1.name)
        assertNotNull(source1.description)
        assertNotNull(source1.url)
        assertNotNull(source1.category)
        assertNotNull(source1.language)
        assertNotNull(source2.country)
        assertNotNull(source1.toString())
        assertNotNull(source1.copy("bah","bah","black","sheep","have",
            "you","any"))
        source1.id = "blah"
        assertEquals(source1.id, "blah")
        source1.name = "blahhhh"
        assertEquals(source1.name,"blahhhh")
        source1.description = "fsahdfa"
        assertEquals(source1.description,"fsahdfa")
        source1.url = "maybe"
        assertEquals(source1.url,"maybe")
        source1.category = "category"
        assertEquals(source1.category,"category")
        source1.language = "language"
        assertEquals(source1.language, "language")
        source1.country = "country"
        assertEquals(source1.country, "country")
    }

    @Test
    fun testMainActivityLaunch() {
        var view = mActivity.findViewById<TextView>(R.id.PageHeader)
        assertNotNull(view)
    }

    @Test
    fun testRecyclerView() {
        var view = mActivity.findViewById<RecyclerView>(R.id.sourceRecyclerView)
        assertNotNull(view)
    }

    @Test
    fun testSwipeRefresh() {
        var refresher = mActivity.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        assertNotNull(refresher)
    }

    @Test
    fun testSwipeRefresher() {
        var refresher = mActivity.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        assertNotNull(refresher)
        assertNotNull(refresher.run { setOnRefreshListener { mActivity.getAllSources() }  })
    }

    @Test
    fun testPrepareRecyclerView() {
        assertNotNull(mActivity.runOnUiThread {mActivity.prepareRecyclerView(input)  } )
    }

    @After
    fun tearDown(){
        mActivity.finish()
    }

}
