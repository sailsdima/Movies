package com.twmaze.movies.data.movies.remote.dto

import com.google.gson.annotations.SerializedName

data class RatingResponse(
    @SerializedName("average") val average: Double,
)