package com.twmaze.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twmaze.common.R

class LoadingItemAdapter : RecyclerView.Adapter<LoadingItemAdapter.LoadingItemViewHolder>() {

    var isLoading: Boolean = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadingItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_loading, parent, false)
        return LoadingItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoadingItemViewHolder, position: Int) {}

    override fun getItemCount(): Int {
        return if (isLoading) 1 else 0
    }

    class LoadingItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}