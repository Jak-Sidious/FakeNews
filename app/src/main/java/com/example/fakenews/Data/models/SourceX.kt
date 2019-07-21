package com.example.fakenews.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SourceX(

    @Expose
    @SerializedName("id")
    var id: String,

    @Expose
    @SerializedName("name")
    var name: String,

    @Expose
    @SerializedName("description")
    var description: String,

    @Expose
    @SerializedName("url")
    var url: String,

    @Expose
    @SerializedName("category")
    val category: String,

    @Expose
    @SerializedName("language")
    val language: String,

    @Expose
    @SerializedName("country")
    val country: String
)
