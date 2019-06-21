package com.example.fakenews.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Source(
    @Expose
    @SerializedName("status")
    val status: String,

    @Expose
    @SerializedName("sources")
    val sources: MutableList<SourceX>
)
