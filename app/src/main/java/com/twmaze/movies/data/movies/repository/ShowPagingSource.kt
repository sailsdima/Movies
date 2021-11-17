package com.twmaze.movies.data.movies.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.twmaze.movies.data.movies.remote.ShowsApiService
import com.twmaze.movies.domain.shows.entity.Show
import com.twmaze.movies.util.toShow
import timber.log.Timber

class ShowPagingSource constructor(
    private val showsApiService: ShowsApiService,
) : PagingSource<Int, Show>() {

    override fun getRefreshKey(state: PagingState<Int, Show>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
        return try {
            val nextPage = params.key ?: 0
            val response = showsApiService.getShows(nextPage).map { it.toShow() }

            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 0) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            Timber.e("Error", "load: $e")
            LoadResult.Error(e)
        }
    }


}