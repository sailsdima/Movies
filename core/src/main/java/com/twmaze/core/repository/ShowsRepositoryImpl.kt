package com.twmaze.core.repository

import com.twmaze.core.api.ShowsApiService
import com.twmaze.core.dto.local.Episode
import com.twmaze.core.dto.local.Show
import com.twmaze.core.dto.local.toEpisode
import com.twmaze.core.dto.local.toShow
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