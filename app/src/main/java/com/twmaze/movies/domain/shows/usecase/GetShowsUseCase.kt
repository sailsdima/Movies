package com.twmaze.movies.domain.shows.usecase

import com.twmaze.movies.domain.shows.ShowsRepository
import com.twmaze.movies.domain.shows.entity.Show
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShowsUseCase @Inject constructor(
    private val showsRepository: ShowsRepository
) {
    suspend fun execute(page: Int): Flow<List<Show>> {
        return showsRepository.getShows(page)
    }

}