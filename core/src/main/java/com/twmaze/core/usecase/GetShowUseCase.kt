package com.twmaze.core.usecase

import com.twmaze.core.dto.local.Episode
import com.twmaze.core.dto.local.Show
import com.twmaze.core.repository.ShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShowUseCase @Inject constructor(
    private val showsRepository: ShowsRepository
) {
    suspend fun execute(id: Long): Flow<Pair<Show, List<Episode>>> {
        return showsRepository.getShowById(id)
    }
}