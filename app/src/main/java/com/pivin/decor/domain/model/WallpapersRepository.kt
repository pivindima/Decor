package com.pivin.decor.domain.model

import androidx.lifecycle.LiveData
import com.pivin.decor.data.database.model.CategoryDbModel
import com.pivin.decor.data.database.model.StaticWallpaperDbModel

interface WallpapersRepository {

    fun getCategories(): LiveData<List<Category>>
    fun getCategoryById(id: Long): LiveData<Category>

    fun getStaticWallpapers(): LiveData<List<StaticWallpaper>>
    fun getStaticWallpaperById(id: Long): LiveData<StaticWallpaper>
    fun getStaticWallpapersByCategory(category: Long): LiveData<List<StaticWallpaper>>
    fun getStaticWallpapersByEd(ed: Boolean): LiveData<List<StaticWallpaper>>

    suspend fun insertCategories(categories: List<CategoryDbModel>)
    suspend fun insertStaticWallpapers(staticWallpapers: List<StaticWallpaperDbModel>)

}