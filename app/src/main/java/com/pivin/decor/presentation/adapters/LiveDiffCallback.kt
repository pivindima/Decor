package com.pivin.decor.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.pivin.decor.domain.model.LiveWallpaper

object LiveDiffCallback : DiffUtil.ItemCallback<LiveWallpaper>() {
    override fun areItemsTheSame(oldItem: LiveWallpaper, newItem: LiveWallpaper): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LiveWallpaper, newItem: LiveWallpaper): Boolean {
        return oldItem == newItem
    }
}