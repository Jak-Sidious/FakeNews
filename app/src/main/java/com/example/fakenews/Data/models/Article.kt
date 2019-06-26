package com.example.fakenews.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Article(
    @Expose
    @SerializedName("source")
    val source: SourceXX,

    @Expose
    @SerializedName("author")
    val author: String,

    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("description")
    val description: String,

    @Expose
    @SerializedName("url")
    val url: String,

    @Expose
    @SerializedName("urlToImage")
    val urlToImage: Any,

    @Expose
    @SerializedName("publishedAt")
    val publishedAt: String,

    @Expose
    @SerializedName("content")
    val content: String
)
