package com.twmaze.core.usecase

import com.twmaze.core.datastore.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteShowsUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    suspend fun execute(): Flow<Set<String>> = dataStoreManager.savedShowIds
}