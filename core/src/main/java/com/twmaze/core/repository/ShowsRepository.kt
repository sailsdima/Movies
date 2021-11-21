package com.twmaze.core.repository

import com.twmaze.core.dto.local.Episode
import com.twmaze.core.dto.local.Show
import kotlinx.coroutines.flow.Flow

interface ShowsRepository {

    suspend fun getShows(page: Int): Flow<List<Show>>
    suspend fun getShowById(id: Long): Flow<Pair<Show, List<Episode>>>
}