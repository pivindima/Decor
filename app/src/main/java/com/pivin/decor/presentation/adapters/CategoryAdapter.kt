package com.pivin.decor.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pivin.decor.databinding.ItemCategoryBinding
import com.pivin.decor.domain.model.Category
import com.squareup.picasso.Picasso

class CategoryAdapter(private val context: Context) : ListAdapter<Category, CategoryViewHolder>(CategoryDiffCallback) {

    var onCategoryClickListener: OnCategoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        with(holder.binding) {
            tvTitle.text = category.name
            tvCategory.text = if (category.live) "Live" else "Static"
            Picasso.get().load(category.imageUrl).into(ivImage)
            root.setOnClickListener {
                onCategoryClickListener?.onClick(category)
            }
        }
    }

    interface OnCategoryClickListener {
        fun onClick(category: Category)
    }

}