package com.twmaze.core.api

import com.twmaze.core.dto.remote.EpisodeResponse
import com.twmaze.core.dto.remote.ShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowsApiService {
    @GET("shows")
    suspend fun getShows(@Query("page") page: Int): List<ShowResponse>

    @GET("shows/{id}")
    suspend fun getShow(@Path("id") showId: Long): ShowResponse

    @GET("shows/{id}/episodes")
    suspend fun getShowEpisodes(@Path("id") showId: Long): List<EpisodeResponse>
}