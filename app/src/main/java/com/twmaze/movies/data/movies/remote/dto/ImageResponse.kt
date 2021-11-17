package com.twmaze.movies.data.movies.remote.dto

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("medium") val medium: String,
    @SerializedName("original") val original: String,
)