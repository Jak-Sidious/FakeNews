package com.example.fakenews.views

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fakenews.R
import com.example.fakenews.adapters.SourceAdapter
import com.example.fakenews.data.models.SourceX
import com.example.fakenews.utils.ClickListener
import com.example.fakenews.utils.RecyclerTouchListener
import com.example.fakenews.viewModels.SourceViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

@Suppress("DEPRECATION", "UNUSED_PARAMETER")
class MainActivity : AppCompatActivity() {

    //TODO() Setup Backup options & Firebase Indexing
    //TODO() Remove suppression warnings and fix them accordingly
    var mRecyclerView: RecyclerView? = null
    var mSwipeRefresh: SwipeRefreshLayout? = null
    var sourceViewModel: SourceViewModel? = null
    var mSourceAdapter: SourceAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.plant(Timber.DebugTree())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSwipeRefresh = swipeRefresh
        mRecyclerView = sourceRecyclerView
        sourceViewModel = ViewModelProviders.of(this).get(SourceViewModel::class.java)
        getAllSources()
        mSwipeRefresh?.setOnRefreshListener {
            getAllSources()
        }
        mSwipeRefresh?.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)
    }

    fun getAllSources(){
        mSwipeRefresh!!.isRefreshing = false
        sourceViewModel!!.allSources.observe(this, Observer { sourceList ->
            prepareRecyclerView(sourceList)
        })
    }

    fun prepareRecyclerView(sourceList: List<SourceX>?) {
        mSourceAdapter = SourceAdapter(sourceList)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView!!.layoutManager = GridLayoutManager(this, PORTRAIT_COUNT)
        } else {
            mRecyclerView!!.layoutManager = GridLayoutManager(this, LANDSCAPE_COUNT)
        }
        mRecyclerView!!.itemAnimator = DefaultItemAnimator()
        mRecyclerView!!.adapter = mSourceAdapter
        mRecyclerView!!.addOnItemTouchListener(
            RecyclerTouchListener(this, mRecyclerView!!, object : ClickListener {
                override fun onClick(view: View, position: Int) {
                    val mainToast = Toast.makeText(this@MainActivity,
                        sourceList!![position].name, Toast.LENGTH_SHORT)
                    mainToast.setGravity(Gravity.CENTER, 0 ,0)
                    mainToast.show()
                    val i = Intent(this@MainActivity, ArticleActivity::class.java)
                    i.putExtra("SourceId", sourceList[position].id)
                    i.putExtra("SourceName", sourceList[position].name)
                    startActivity(i)
                }
            })
        )
    }

    companion object {
        private const val LANDSCAPE_COUNT = 4
        private const val PORTRAIT_COUNT = 2
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.search)?.actionView as SearchView).apply{
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            queryHint = "Search Latest News...."
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    if (query.length > 2) {
                        Toast.makeText(this@MainActivity, "Searching for $query", Toast.LENGTH_SHORT).show()
                        val searchIntent = Intent(this@MainActivity, ArticleActivity::class.java)
                        searchIntent.putExtra("SearchQuery", query)
                        startActivity(searchIntent)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
        }
        return true
    }
}
