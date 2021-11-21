package com.twmaze.show.single

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.twmaze.core.dto.local.Episode
import com.twmaze.show.R
import com.twmaze.util.openLink

class EpisodeAdapter(private val episodes: List<Episode>) :
    RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % 3 == 0) 2 else if ((position + 1) % 2 == 0) 1 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = episodes[position]
        holder.itemView.run {
            Glide.with(holder.itemView.context)
                .load(episode.image.medium)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
                .into(findViewById(R.id.ivCover))

            findViewById<TextView>(R.id.tvTitle).text = episode.name
            findViewById<TextView>(R.id.tvRate).text = "${episode.rating.average}"
            findViewById<TextView>(R.id.tvEpisodeNumber).text =
                "Season: ${episode.season}, Episode: ${episode.number}"
            setOnClickListener { context.openLink(episode.url) }
        }

    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view)

}