package com.pivin.decor.presentation.view_models

import androidx.lifecycle.ViewModel
import com.pivin.decor.domain.model.WallpapersRepository
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val repository: WallpapersRepository,
) : ViewModel() {
    fun categoryList() = repository.getCategories()
}