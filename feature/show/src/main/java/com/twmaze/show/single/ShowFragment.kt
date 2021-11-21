package com.twmaze.show.single

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.twmaze.common.adapter.GenreAdapter
import com.twmaze.common.adapter.GenreHeaderAdapter
import com.twmaze.common.base.BaseFragment
import com.twmaze.show.R
import com.twmaze.util.openLink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowFragment : BaseFragment<ShowViewModel.State, ShowViewModel.Action>(R.layout.fragment_show) {

    override val viewModel: ShowViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setShowId(requireArguments().get("showId") as Long)

        view.findViewById<ImageView>(R.id.ivBack)
            .setOnClickListener { findNavController().popBackStack() }
    }

    override fun renderState(state: ShowViewModel.State) {
        val show = state.show
        view?.run {
            Glide.with(requireContext())
                .load(show.image.medium)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
                .into(findViewById(R.id.ivCover))

            findViewById<TextView>(R.id.tvToolbar).text = show.name
            findViewById<TextView>(R.id.tvTitle).text = show.name
            findViewById<TextView>(R.id.tvType).text = show.type
            findViewById<TextView>(R.id.tvStatus).text = show.status
            findViewById<TextView>(R.id.tvLanguage).text = show.language
            findViewById<TextView>(R.id.tvRate).text = "${show.rating.average}"
            findViewById<TextView>(R.id.tvDescription).text = show.summary
            findViewById<RecyclerView>(R.id.rvGenres).run {
                val headerAdapter = GenreHeaderAdapter()
                val genreAdapter = GenreAdapter(show.genres)
                val concatAdapter = ConcatAdapter(headerAdapter, genreAdapter)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = concatAdapter
            }

            findViewById<RecyclerView>(R.id.rvEpisodes).run {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = EpisodeAdapter(state.episodes)
            }
            findViewById<Button>(R.id.btnToggleFav).run {
                text = if (state.isFavorite) "Remove" else "Add"
                setOnClickListener {
                    if (state.isFavorite) viewModel.removeFromFavorite(show)
                    else viewModel.addToFavorite(show)
                }
            }
            findViewById<Button>(R.id.btnOpenInWeb).setOnClickListener { context.openLink(show.url) }
            findViewById<View>(R.id.pbLoader).isVisible = false
            findViewById<View>(R.id.svContent).isVisible = true
        }
    }

    override fun handleViewAction(action: ShowViewModel.Action) {
        when (action) {
            is ShowViewModel.Action.ShowErrorToast -> Toast.makeText(
                requireContext(),
                "Error occurred",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}