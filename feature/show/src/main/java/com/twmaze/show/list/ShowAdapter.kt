package com.twmaze.show.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.twmaze.common.adapter.GenreAdapter
import com.twmaze.common.adapter.GenreHeaderAdapter
import com.twmaze.core.dto.local.Show
import com.twmaze.show.R

class ShowAdapter : PagingDataAdapter<Show, ShowAdapter.ShowViewHolder>(ShowComparator) {

    var onItemClick: ((Show) -> Unit)? = null
    var onAddToFavouriteClick: ((Show, Int) -> Unit)? = null
    var onRemoveFromFavouriteClick: ((Show, Int) -> Unit)? = null
    var favorites: Set<String> = emptySet()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_show, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val item = getItem(position)!!
        val isFavorite = favorites.contains(item.id.toString())

        holder.itemView.run {
            Glide.with(holder.itemView.context)
                .load(item.image.medium)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
                .into(findViewById(R.id.ivCover))


            setOnClickListener { onItemClick?.invoke(item) }
            findViewById<TextView>(R.id.tvTitle).text = item.name
            findViewById<TextView>(R.id.tvType).text = item.type
            findViewById<TextView>(R.id.tvStatus).text = item.status
            findViewById<TextView>(R.id.tvLanguage).text = item.language
            findViewById<TextView>(R.id.tvRate).text = "${item.rating.average}"
            findViewById<ImageView>(R.id.ivFavorite).run {
                setOnClickListener {
                    if (isFavorite)
                        onRemoveFromFavouriteClick?.invoke(item, position)
                    else
                        onAddToFavouriteClick?.invoke(item, position)
                }
                isSelected = isFavorite
            }
            findViewById<RecyclerView>(R.id.rvGenres).run {
                val headerAdapter = GenreHeaderAdapter()
                val genreAdapter = GenreAdapter(item.genres)
                val concatAdapter = ConcatAdapter(headerAdapter, genreAdapter)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = concatAdapter
            }
        }
    }

    class ShowViewHolder(view: View) : RecyclerView.ViewHolder(view)

    object ShowComparator : DiffUtil.ItemCallback<Show>() {
        override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem == newItem
        }
    }

}