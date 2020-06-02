package com.example.meditate2.photoactivities

import com.google.gson.annotations.SerializedName

data class PhotoModel(

    @SerializedName("raw")
    val url: String,
    @SerializedName("id")
    val id: String
)