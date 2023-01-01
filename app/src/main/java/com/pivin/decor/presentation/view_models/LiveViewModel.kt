package com.pivin.decor.presentation.view_models

import androidx.lifecycle.ViewModel
import com.pivin.decor.domain.model.WallpapersRepository
import javax.inject.Inject

class LiveViewModel @Inject constructor(
    private val repository: WallpapersRepository,
) : ViewModel() {

    fun liveWallpapersList() = repository.getLiveWallpapers()

    fun liveWallpapersByCategory(categoryId: Long, image: String) =
        if (image.contains("editors")) repository.getLiveWallpapersByEd(true) else repository.getLiveWallpapersByCategory(categoryId)

    fun liveWallpaper(id: Long) = repository.getLiveWallpaperById(id)
}