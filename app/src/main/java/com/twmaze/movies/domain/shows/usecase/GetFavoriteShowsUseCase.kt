package com.twmaze.movies.domain.shows.usecase

import com.twmaze.movies.datastore.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteShowsUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    suspend fun execute(): Flow<Set<String>> = dataStoreManager.savedShowIds
}