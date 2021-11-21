package com.twmaze.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twmaze.common.R

class GenreHeaderAdapter : RecyclerView.Adapter<GenreHeaderAdapter.GenreHeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genre_header, parent, false)
        return GenreHeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreHeaderViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 1
    }

    class GenreHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view)

}