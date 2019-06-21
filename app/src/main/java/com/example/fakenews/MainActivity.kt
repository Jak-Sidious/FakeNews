package com.example.fakenews

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fakenews.data.models.SourceX
import com.example.fakenews.adapters.SourceAdapter
import com.example.fakenews.viewModels.SourceViewModel
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    var mRecyclerView: RecyclerView? = null
    var swipeRefresh: SwipeRefreshLayout? = null
    var sourceViewModel: SourceViewModel? = null

    var mSourceAdapter: SourceAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefresh = swipeRefresh
        mRecyclerView = sourceRecyclerView
        sourceViewModel = ViewModelProviders.of(this).get(SourceViewModel::class.java)
        getAllSources()
//        swipeRefresh!!.setOnRefreshListener { getAllSources() }
    }

    fun getAllSources(){
//        swipeRefresh!!.isRefreshing = false
        sourceViewModel!!.allSources.observe(this, Observer { sourceList ->
            prepareRecyclerView(sourceList)
        })
    }

    private fun prepareRecyclerView(sourceList: List<SourceX>?) {
        mSourceAdapter = SourceAdapter(sourceList)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        } else {
            mRecyclerView!!.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        }
        mRecyclerView!!.itemAnimator = DefaultItemAnimator()
        mRecyclerView!!.adapter = mSourceAdapter
    }

    companion object {
        private const val SPAN_COUNT = 42
    }
}


