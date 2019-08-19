package com.example.fakenews.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakenews.R
import com.example.fakenews.data.models.Article
import java.util.*
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat


class ArticleAdapter(val articleList: List<Article>?) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    override fun getItemCount() = articleList!!.size
    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.mContext=parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mArticle = articleList!![position]
        Glide.with(mContext!!)
            .load(mArticle.urlToImage)
            .into(holder.mArticleImage)
        holder.mArticleTitle.text = mArticle.title
        holder.mArticleAuthor.text = mArticle.author
        holder.mArticleTimestamp.text = dateToTime(mArticle.publishedAt)
        holder.mArticleDescription.text = mArticle.description
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mArticleImage: ImageView = itemView.findViewById(R.id.article_image)
        val mArticleTitle: TextView = itemView.findViewById(R.id.article_title)
        val mArticleAuthor: TextView = itemView.findViewById(R.id.article_author)
        val mArticleTimestamp: TextView = itemView.findViewById(R.id.article_timestamp)
        val mArticleDescription: TextView = itemView.findViewById(R.id.article_description)
    }

    fun getCountry() : String {
        var locale = Locale.getDefault()
        var country = locale.country
        return country.toLowerCase()
    }

    fun dateToTime(providedDate: String) : String? {
        var p = PrettyTime(Locale(getCountry()))
        var isTime: String? = null
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            val date = sdf.parse(providedDate)
            isTime = p.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return isTime
    }
}
