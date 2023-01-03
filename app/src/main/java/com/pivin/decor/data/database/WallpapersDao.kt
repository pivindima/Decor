package com.pivin.decor.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pivin.decor.data.database.model.CategoryDbModel
import com.pivin.decor.data.database.model.StaticWallpaperDbModel

@Dao
interface WallpapersDao {

    @Query("SELECT * FROM category ORDER BY RANDOM()")
    fun getCategories(): LiveData<List<CategoryDbModel>>

    @Query("SELECT * FROM category WHERE id == :id LIMIT 1")
    fun getCategoryById(id: Long): LiveData<CategoryDbModel>


    @Query("SELECT * FROM static_wallpaper ORDER BY RANDOM()")
    fun getStaticWallpapers(): LiveData<List<StaticWallpaperDbModel>>

    @Query("SELECT * FROM static_wallpaper WHERE id == :id LIMIT 1")
    fun getStaticWallpaperById(id: Long): LiveData<StaticWallpaperDbModel>

    @Query("SELECT * FROM static_wallpaper WHERE category == :category ORDER BY date ASC")
    fun getStaticWallpapersByCategory(category: Long): LiveData<List<StaticWallpaperDbModel>>

    @Query("SELECT * FROM static_wallpaper WHERE ed == :ed ORDER BY date ASC")
    fun getStaticWallpapersByEd(ed: Boolean): LiveData<List<StaticWallpaperDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStaticWallpapers(staticWallpapers: List<StaticWallpaperDbModel>)

}