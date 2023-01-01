package com.pivin.decor.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pivin.decor.databinding.ItemStaticBinding
import com.pivin.decor.domain.model.StaticWallpaper
import com.squareup.picasso.Picasso

class StaticAdapter(private val context: Context) : ListAdapter<StaticWallpaper, StaticViewHolder>(StaticDiffCallback) {

    var onStaticClickListener: OnStaticClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaticViewHolder {
        val binding = ItemStaticBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StaticViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StaticViewHolder, position: Int) {
        val staticWallpaper = getItem(position)
        with(holder.binding) {
            Picasso.get().load(staticWallpaper.urlThumb).into(ivImage)
            root.setOnClickListener {
                onStaticClickListener?.onClick(staticWallpaper)
            }
        }
    }

    interface OnStaticClickListener {
        fun onClick(staticWallpaper: StaticWallpaper)
    }

}