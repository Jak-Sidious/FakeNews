package com.example.fakenews.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SourceXX(
    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("name")
    val name: String

)
