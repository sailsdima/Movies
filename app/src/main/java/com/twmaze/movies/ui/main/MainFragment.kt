package com.twmaze.movies.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.twmaze.movies.R
import com.twmaze.movies.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel.State, MainViewModel.Action>(R.layout.fragment_main) {

    private lateinit var showAdapter: ShowAdapter

    override val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAdapter = ShowAdapter().apply {
            onItemClick = {
                findNavController().navigate(MainFragmentDirections.toShowFragment(it.id))
            }
            onAddToFavouriteClick = { show, pos ->
                viewModel.addToFavorite(show, pos)
            }
            onRemoveFromFavouriteClick = { show, pos ->
                viewModel.removeFromFavorite(show, pos)
            }
        }
        refreshData()

        val loadingItemAdapter = LoadingItemAdapter()
        val concatAdapter = ConcatAdapter(showAdapter, loadingItemAdapter)

        view.findViewById<RecyclerView>(R.id.rvShows).apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = concatAdapter
        }

        showAdapter.addLoadStateListener { loadState ->
            val isRefreshing = loadState.refresh is LoadState.Loading
            view.findViewById<ProgressBar>(R.id.progressBar).isVisible = isRefreshing
            view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout).isRefreshing =
                isRefreshing

            val isRefreshError = loadState.refresh is LoadState.Error
            view.findViewById<TextView>(R.id.tvError).isVisible = isRefreshError
            view.findViewById<RecyclerView>(R.id.rvShows).isVisible = !isRefreshError

            val isLoadingAppend = loadState.append is LoadState.Loading
            loadingItemAdapter.isLoading = isLoadingAppend
        }
    }

    override fun renderState(state: MainViewModel.State) {
        showAdapter.favorites = state.favorites
    }

    override fun handleViewAction(action: MainViewModel.Action) {
        when (action) {
            is MainViewModel.Action.NotifyItemChanged -> showAdapter.notifyItemChanged(action.pos)
        }
    }

    private fun refreshData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movies.collectLatest {
                showAdapter.submitData(it)
            }
        }
    }
}