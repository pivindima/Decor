package com.pivin.decor.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.pivin.decor.domain.model.Category
import com.pivin.decor.domain.model.StaticWallpaper

object StaticDiffCallback : DiffUtil.ItemCallback<StaticWallpaper>() {
    override fun areItemsTheSame(oldItem: StaticWallpaper, newItem: StaticWallpaper): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StaticWallpaper, newItem: StaticWallpaper): Boolean {
        return oldItem == newItem
    }
}