package com.twmaze.core.usecase

import com.twmaze.core.dto.local.Show
import com.twmaze.core.repository.ShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShowsUseCase @Inject constructor(
    private val showsRepository: ShowsRepository
) {
    suspend fun execute(page: Int): Flow<List<Show>> {
        return showsRepository.getShows(page)
    }

}