package com.example.fakenews.views

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import com.example.fakenews.R
import com.example.fakenews.data.models.SourceX
import org.junit.Before
import org.junit.runner.RunWith
import com.example.fakenews.viewModels.SourceViewModel
import kotlinx.coroutines.runBlocking
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.view.size
import androidx.lifecycle.Observer
import com.example.fakenews.adapters.SourceAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mActivity: MainActivity
    lateinit var subject : SourceViewModel
    lateinit var source1: SourceX
    lateinit var source2: SourceX
    lateinit var input: List<SourceX>
    lateinit var adapted: SourceAdapter

    @Before
    fun setUp() {
        mActivity =  rule.activity
        mActivity.mRecyclerView
//        mActivity.sourceViewModel
        subject = SourceViewModel()
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
        source1 = SourceX("a","b","c", "d", "e", "f", "g")
        source2 = SourceX("h","i","j","k","l","m","n")
        input = listOf(source1, source2)

        assertNotNull(mActivity.runOnUiThread {mActivity.prepareRecyclerView(input)  } )
    }
}
