package com.twmaze.show.single

import androidx.lifecycle.viewModelScope
import com.twmaze.common.base.BaseViewModel
import com.twmaze.core.dto.local.Episode
import com.twmaze.core.dto.local.Show
import com.twmaze.core.usecase.AddToFavoriteUseCase
import com.twmaze.core.usecase.GetFavoriteShowsUseCase
import com.twmaze.core.usecase.GetShowUseCase
import com.twmaze.core.usecase.RemoveFromFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(
    private val getShowUseCase: GetShowUseCase,
    private val getFavoriteShowsUseCase: GetFavoriteShowsUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val removeFromFavoriteUseCase: RemoveFromFavoriteUseCase,
) : BaseViewModel<ShowViewModel.State, ShowViewModel.Action>() {

    fun setShowId(showId: Long) {
        loadShow(showId)
    }

    fun addToFavorite(show: Show) {
        viewModelScope.launch {
            try {
                addToFavoriteUseCase.execute(show.id)
            } catch (e: Exception) {
                Timber.e("Error addToFavorite: ${e.message}")
            }
        }
    }

    fun removeFromFavorite(show: Show) {
        viewModelScope.launch {
            try {
                removeFromFavoriteUseCase.execute(show.id)
            } catch (e: Exception) {
                Timber.e("Error removeFromFavorite: ${e.message}")
            }
        }
    }

    private fun loadShow(showId: Long) {
        viewModelScope.launch {
            try {
                getShowUseCase.execute(showId).collectLatest {
                    Timber.i("loadShow  $it")
                    renderState(State(show = it.first, episodes = it.second, isFavorite = false))
                    checkForFavorites()
                }
            } catch (e: Exception) {
                Timber.e("Error loadShow: ${e.message}")
            }
        }
    }

    private fun checkForFavorites() {
        viewModelScope.launch {
            try {
                getFavoriteShowsUseCase.execute().collectLatest {
                    mutateState { copy(isFavorite = it.contains(show.id.toString())) }
                }
            } catch (e: Exception) {
                Timber.e("Error checkForFavorites: ${e.message}")
            }
        }
    }

    data class State(
        val show: Show,
        val episodes: List<Episode>,
        val isFavorite: Boolean,
    )

    sealed class Action {
        object ShowErrorToast : Action()
    }

}