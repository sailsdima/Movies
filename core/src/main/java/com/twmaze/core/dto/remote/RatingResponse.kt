package com.twmaze.core.dto.remote

import com.google.gson.annotations.SerializedName

data class RatingResponse(
    @SerializedName("average") val average: Double,
)