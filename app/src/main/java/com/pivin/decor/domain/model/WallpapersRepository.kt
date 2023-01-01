package com.pivin.decor.domain.model

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.pivin.decor.data.database.model.CategoryDbModel
import com.pivin.decor.data.database.model.LiveWallpaperDbModel
import com.pivin.decor.data.database.model.StaticWallpaperDbModel

interface WallpapersRepository {

    fun getCategories(): LiveData<List<Category>>
    fun getCategoryById(id: Long): LiveData<Category>

    fun getStaticWallpapers(): LiveData<List<StaticWallpaper>>
    fun getStaticWallpaperById(id: Long): LiveData<StaticWallpaper>
    fun getStaticWallpapersByCategory(category: Long): LiveData<List<StaticWallpaper>>
    fun getStaticWallpapersByEd(ed: Boolean): LiveData<List<StaticWallpaper>>

    fun getLiveWallpapers(): LiveData<List<LiveWallpaper>>
    fun getLiveWallpaperById(id: Long): LiveData<LiveWallpaper>
    fun getLiveWallpapersByCategory(category: Long): LiveData<List<LiveWallpaper>>
    fun getLiveWallpapersByEd(ed: Boolean): LiveData<List<LiveWallpaper>>

    suspend fun insertCategories(categories: List<CategoryDbModel>)
    suspend fun insertStaticWallpapers(staticWallpapers: List<StaticWallpaperDbModel>)
    suspend fun insertLiveWallpapers(liveWallpapers: List<LiveWallpaperDbModel>)

}