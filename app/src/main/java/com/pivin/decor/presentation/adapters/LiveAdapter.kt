package com.pivin.decor.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pivin.decor.databinding.ItemLiveBinding
import com.pivin.decor.domain.model.LiveWallpaper
import com.squareup.picasso.Picasso

class LiveAdapter(private val context: Context) : ListAdapter<LiveWallpaper, LiveViewHolder>(LiveDiffCallback) {

    var onLiveClickListener: OnLiveClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveViewHolder {
        val binding = ItemLiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LiveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LiveViewHolder, position: Int) {
        val liveWallpaper = getItem(position)
        with(holder.binding) {
            Picasso.get().load(liveWallpaper.urlThumb).into(ivImage)
            root.setOnClickListener {
                onLiveClickListener?.onClick(liveWallpaper)
            }
        }
    }

    interface OnLiveClickListener {
        fun onClick(liveWallpaper: LiveWallpaper)
    }

}