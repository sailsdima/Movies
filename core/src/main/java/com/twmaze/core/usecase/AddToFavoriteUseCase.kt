package com.twmaze.core.usecase

import com.twmaze.core.datastore.DataStoreManager
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    suspend fun execute(id: Long) {
        return dataStoreManager.saveShowId(id.toString())
    }
}