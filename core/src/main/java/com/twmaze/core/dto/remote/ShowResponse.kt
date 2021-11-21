package com.twmaze.core.dto.remote

import com.google.gson.annotations.SerializedName

data class ShowResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("url") val url: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("language") val language: String,
    @SerializedName("status") val status: String,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("averageRuntime") val averageRuntime: Int,
    @SerializedName("premiered") val premiered: String?,
    @SerializedName("ended") val ended: String?,
    @SerializedName("rating") val rating: RatingResponse,
    @SerializedName("image") val image: ImageResponse,
    @SerializedName("summary") val summary: String,
)