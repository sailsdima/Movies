package com.twmaze.movies.data.movies.remote

import com.twmaze.movies.data.movies.remote.dto.EpisodeResponse
import com.twmaze.movies.data.movies.remote.dto.ShowResponse
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