package com.pivin.decor.presentation.view_models

import androidx.lifecycle.ViewModel
import com.pivin.decor.domain.model.WallpapersRepository
import javax.inject.Inject

class StaticViewModel @Inject constructor(
    private val repository: WallpapersRepository,
) : ViewModel() {

    fun staticWallpapersList() = repository.getStaticWallpapers()

    fun staticWallpapersByCategory(categoryId: Long, image: String) =
        if (image.contains("editors")) repository.getStaticWallpapersByEd(true) else repository.getStaticWallpapersByCategory(categoryId)

    fun staticWallpaper(id: Long) = repository.getStaticWallpaperById(id)
}