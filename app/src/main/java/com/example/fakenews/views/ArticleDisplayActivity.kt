package com.example.fakenews.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fakenews.R
import kotlinx.android.synthetic.main.web_view.*
import timber.log.Timber

class ArticleDisplayActivity : AppCompatActivity() {

    var storyUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)
        var recieved: Bundle = intent.extras
        storyUrl = recieved.getString("ArticleUrl")
        Timber.d("The site URL $storyUrl")
        article_viewer.loadUrl(storyUrl)
    }
}
