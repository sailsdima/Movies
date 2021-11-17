package com.twmaze.movies.ui.main

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.twmaze.movies.data.movies.remote.ShowsApiService
import com.twmaze.movies.data.movies.repository.ShowPagingSource
import com.twmaze.movies.domain.shows.entity.Show
import com.twmaze.movies.domain.shows.usecase.AddToFavoriteUseCase
import com.twmaze.movies.domain.shows.usecase.GetFavoriteShowsUseCase
import com.twmaze.movies.domain.shows.usecase.RemoveFromFavoriteUseCase
import com.twmaze.movies.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val showsApiService: ShowsApiService,
    private val getFavoriteShowsUseCase: GetFavoriteShowsUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val removeFromFavoriteUseCase: RemoveFromFavoriteUseCase,
) : BaseViewModel<MainViewModel.State, MainViewModel.Action>() {

    val movies: Flow<PagingData<Show>> = Pager(PagingConfig(pageSize = 25)) {
        ShowPagingSource(showsApiService)
    }.flow
        .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            getFavoriteShowsUseCase.execute().collectLatest {
                renderState(State(it))
            }
        }
    }

    fun addToFavorite(show: Show, pos: Int) {
        viewModelScope.launch {
            try {
                addToFavoriteUseCase.execute(show.id)
                postViewAction(Action.NotifyItemChanged(pos))
            } catch (e: Exception) {
                Timber.e("Error addToFavorite: ${e.message}")
            }
        }
    }

    fun removeFromFavorite(show: Show, pos: Int) {
        viewModelScope.launch {
            try {
                removeFromFavoriteUseCase.execute(show.id)
                postViewAction(Action.NotifyItemChanged(pos))
            } catch (e: Exception) {
                Timber.e("Error removeFromFavorite: ${e.message}")
            }
        }
    }

    data class State(
        val favorites: Set<String>
    )

    sealed class Action {
        class NotifyItemChanged(val pos: Int) : Action()
    }

}