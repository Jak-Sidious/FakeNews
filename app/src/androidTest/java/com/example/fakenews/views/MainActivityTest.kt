package com.example.fakenews.views

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.fakenews.R
import org.junit.Before
import org.junit.runner.RunWith


@LargeTest
//@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    lateinit var mActivity: MainActivity


    var monitor = getInstrumentation().addMonitor(
        ArticleActivity::class.java.name, null, false
    )


    @Before
    fun setUp() {
        mActivity =  rule.activity
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
}
