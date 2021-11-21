package com.twmaze.core.usecase

import com.twmaze.core.datastore.DataStoreManager
import javax.inject.Inject

class RemoveFromFavoriteUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    suspend fun execute(id: Long) {
        return dataStoreManager.deleteShowId(id.toString())
    }
}