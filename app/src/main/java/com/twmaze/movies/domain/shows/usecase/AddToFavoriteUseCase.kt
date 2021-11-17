package com.twmaze.movies.domain.shows.usecase

import com.twmaze.movies.datastore.DataStoreManager
import com.twmaze.movies.domain.shows.ShowsRepository
import com.twmaze.movies.domain.shows.entity.Episode
import com.twmaze.movies.domain.shows.entity.Show
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    suspend fun execute(id: Long) {
        return dataStoreManager.saveShowId(id.toString())
    }
}