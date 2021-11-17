package com.twmaze.movies.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.twmaze.util.NavigationCommand
import com.twmaze.util.livedata.liveData
import com.twmaze.util.livedata.liveEvent

abstract class BaseViewModel<State : Any, Action : Any> : ViewModel() {
    private val _actionData by liveEvent<Action>()
    val actionData: LiveData<Action> = _actionData

    private val _navigationData by liveEvent<NavigationCommand>()
    val navigationData: LiveData<NavigationCommand> = _navigationData

    private val _displayState by liveData<State>()
    val displayState: LiveData<State> = _displayState

    private val _progressData by liveData<Boolean>()
    val progressData: LiveData<Boolean> = _progressData

    protected fun renderState(state: State) {
        _displayState.value = state
    }

    protected fun postViewAction(action: Action) {
        _actionData.value = action
    }

    protected fun showProgress() {
        _progressData.value = true
    }

    protected fun hideProgress() {
        _progressData.value = false
    }

    protected fun mutateState(mutation: State.() -> State) {
        displayState.value?.let {
            renderState(it.mutation())
        }
    }

}