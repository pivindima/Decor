package com.pivin.decor.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.pivin.decor.domain.model.Category

object CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}