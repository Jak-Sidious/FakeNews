package com.example.fakenews.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticlesPerSource(
    @Expose
    @SerializedName("status")
    val status: String,

    @Expose
    @SerializedName("totalResults")
    val totalResults: Int,

    @Expose
    @SerializedName("articles")
    val articles: List<Article>
)
