package com.twmaze.core.dto.remote

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("url") val url: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("season") val season: Int,
    @SerializedName("number") val number: Int,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("rating") val rating: RatingResponse,
    @SerializedName("image") val image: ImageResponse,
    @SerializedName("summary") val summary: String,
)