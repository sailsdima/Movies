package com.twmaze.movies.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.twmaze.movies.R

class GenreAdapter(private val genres: List<String>) :
    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % 3 == 0) 2 else if ((position + 1) % 2 == 0) 1 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val layout = when (viewType) {
            0 -> R.layout.item_genre_yellow
            1 -> R.layout.item_genre_teal
            else -> R.layout.item_genre_purple
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvGenreName).text = genres[position]
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view)

}