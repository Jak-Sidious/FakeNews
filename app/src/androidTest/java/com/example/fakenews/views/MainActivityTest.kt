package com.example.fakenews.views

import android.content.Intent
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
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
import androidx.test.filters.LargeTest
import com.example.fakenews.utils.ClickListener
import com.example.fakenews.utils.RecyclerTouchListener


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
    lateinit var input: List<SourceX>

    @Before
    fun setUp() {
        mActivity =  rule.activity
        mActivity.mRecyclerView
        source1 = SourceX("a","b","c", "d", "e", "f", "g")
        source2 = SourceX("h","i","j","k","l","m","n")
        input = listOf(source1, source2)
    }


    @Test
    fun testMainActivityLaunch() {
        var view = mActivity.findViewById<TextView>(R.id.PageHeader)
        assertNotNull(view)
    }

    @Test
    fun testRecyclerView(){
        var view = mActivity.findViewById<RecyclerView>(R.id.sourceRecyclerView)
        assertNotNull(view)
    }

    @Test
    fun testPrepareRecyclerView() {

        assertNotNull(mActivity.runOnUiThread {mActivity.prepareRecyclerView(input)  } )
    }

    @Test
    fun testRecyclerViewOnCLickLaunchesArticleActivity() {
        mActivity.setVisible(true)
        var recycler = mActivity.runOnUiThread { mActivity.prepareRecyclerView(input) }
        assertNotNull(recycler)
        var recycled = mActivity.findViewById<RecyclerView>(R.id.sourceRecyclerView)
        recycled.measure(0,0)
        recycled.layout(0,0, 100, 100)
        var clicker = mActivity.runOnUiThread {   recycled.addOnItemTouchListener(
            RecyclerTouchListener(mActivity.applicationContext, recycled, object: ClickListener{
            override fun onClick(view: View, position: Int) {
                val mainToast = Toast.makeText(mActivity.applicationContext, input[0].name,
                    Toast.LENGTH_SHORT)
                assertNotNull(mainToast)
                mainToast.setGravity(Gravity.CENTER, 0 ,0)
                mainToast.show()
                assertNotNull(mainToast.show())
                val i = Intent(mActivity.applicationContext, ArticleActivity::class.java)
                i.putExtra("SourceId", input[0].id)
                i.putExtra("SourceName", input[0].name)
                assertNotNull(mActivity.startActivity(i))
            }

        }))}
        assertNotNull(clicker)
        recycled.findViewHolderForAdapterPosition(0)?.itemView?.performClick()?.let { assertTrue(it) }

    }

}
