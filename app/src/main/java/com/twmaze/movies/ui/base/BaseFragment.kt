package com.twmaze.movies.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.twmaze.util.NavigationCommand


abstract class BaseFragment<State : Any, Action : Any>(@LayoutRes contentLayoutId: Int) :
    Fragment(contentLayoutId) {

    abstract val viewModel: BaseViewModel<State, Action>

    open fun handleViewAction(action: Action) {
    }

    open fun renderState(state: State) {
    }

    open fun showProgress(show: Boolean) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.let { viewModel ->
            viewModel.displayState.observe(viewLifecycleOwner, { renderState(it) })
            viewModel.actionData.observe(viewLifecycleOwner, { handleViewAction(it) })
            viewModel.progressData.observe(viewLifecycleOwner, { showProgress(it) })
            viewModel.navigationData.observe(viewLifecycleOwner, { handleNavigationEvent(it) })
        }
    }

    private fun handleNavigationEvent(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.To -> findNavController().navigate(command.directions)
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }
}