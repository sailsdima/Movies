package com.twmaze.core.dto.remote

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("medium") val medium: String,
    @SerializedName("original") val original: String,
)