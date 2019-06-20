package com.example.fakenews.Data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SourceX(

    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("description")
    val description: String,

    @Expose
    @SerializedName("url")
    val url: String,

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