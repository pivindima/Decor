package com.pivin.decor.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.pivin.decor.data.database.WallpapersDao
import com.pivin.decor.data.database.model.CategoryDbModel
import com.pivin.decor.data.database.model.StaticWallpaperDbModel
import com.pivin.decor.data.mapper.Mapper
import com.pivin.decor.domain.model.Category
import com.pivin.decor.domain.model.StaticWallpaper
import com.pivin.decor.domain.model.WallpapersRepository
import javax.inject.Inject

class WallpapersRepositoryImpl @Inject constructor(
    private val wallpapersDao: WallpapersDao,
    private val mapper: Mapper
) : WallpapersRepository {

    override fun getCategories(): LiveData<List<Category>> {
        return Transformations.map(wallpapersDao.getCategories()) {
            it.map { categoryDbModel ->
                mapper.mapCategoryDbModelToEntity(categoryDbModel)
            }
        }
    }

    override fun getCategoryById(id: Long): LiveData<Category> {
        return Transformations.map(wallpapersDao.getCategoryById(id)) {
            mapper.mapCategoryDbModelToEntity(it)
        }

    }

    override fun getStaticWallpapers(): LiveData<List<StaticWallpaper>> {
        return Transformations.map(wallpapersDao.getStaticWallpapers()) {
            it.map { staticWallpaperDbModel ->
                mapper.mapStaticWallpaperDbModelToEntity(staticWallpaperDbModel)
            }
        }
    }

    override fun getStaticWallpaperById(id: Long): LiveData<StaticWallpaper> {
        return Transformations.map(wallpapersDao.getStaticWallpaperById(id)) {
            mapper.mapStaticWallpaperDbModelToEntity(it)
        }
    }

    override fun getStaticWallpapersByCategory(category: Long): LiveData<List<StaticWallpaper>> {
        return Transformations.map(wallpapersDao.getStaticWallpapersByCategory(category)) {
            it.map { staticWallpaperDbModel ->
                mapper.mapStaticWallpaperDbModelToEntity(staticWallpaperDbModel)
            }
        }
    }

    override fun getStaticWallpapersByEd(ed: Boolean): LiveData<List<StaticWallpaper>> {
        return Transformations.map(wallpapersDao.getStaticWallpapersByEd(ed)) {
            it.map { staticWallpaperDbModel ->
                mapper.mapStaticWallpaperDbModelToEntity(staticWallpaperDbModel)
            }
        }
    }

    override suspend fun insertCategories(categories: List<CategoryDbModel>) {
        wallpapersDao.insertCategories(categories)
    }

    override suspend fun insertStaticWallpapers(staticWallpapers: List<StaticWallpaperDbModel>) {
        wallpapersDao.insertStaticWallpapers(staticWallpapers)
    }

}