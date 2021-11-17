package com.twmaze.movies.data.movies.repository

import com.twmaze.movies.data.movies.remote.ShowsApiService
import com.twmaze.movies.domain.shows.ShowsRepository
import com.twmaze.movies.domain.shows.entity.Episode
import com.twmaze.movies.domain.shows.entity.Show
import com.twmaze.movies.util.toEpisode
import com.twmaze.movies.util.toShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShowsRepositoryImpl @Inject constructor(
    private val showsApiService: ShowsApiService
) : ShowsRepository {

    override suspend fun getShows(page: Int): Flow<List<Show>> {
        return flow {
            emit(showsApiService.getShows(page).map { it.toShow() })
        }
    }

    override suspend fun getShowById(id: Long): Flow<Pair<Show, List<Episode>>> {
        return flow {
            val show = showsApiService.getShow(id).toShow()
            val episodes = showsApiService.getShowEpisodes(id).map { it.toEpisode() }

            emit(show to episodes)
        }
    }
}