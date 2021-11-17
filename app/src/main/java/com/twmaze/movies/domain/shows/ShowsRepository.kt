package com.twmaze.movies.domain.shows

import com.twmaze.movies.domain.shows.entity.Episode
import com.twmaze.movies.domain.shows.entity.Show
import kotlinx.coroutines.flow.Flow

interface ShowsRepository {

    suspend fun getShows(page: Int): Flow<List<Show>>
    suspend fun getShowById(id: Long): Flow<Pair<Show, List<Episode>>>
}