package com.pivin.decor.data.mapper

import com.pivin.decor.data.database.model.CategoryDbModel
import com.pivin.decor.data.database.model.StaticWallpaperDbModel
import com.pivin.decor.data.network.model.CategoryDto
import com.pivin.decor.data.network.model.StaticWallpaperDto
import com.pivin.decor.domain.model.Category
import com.pivin.decor.domain.model.StaticWallpaper
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapCategoryDtoToDbModel(dto: CategoryDto) = CategoryDbModel(
        dto.id,
        dto.name,
        dto.image,
        dto.live,
        dto.date
    )

    fun mapStaticWallpaperDtoToDbModel(dto: StaticWallpaperDto) = StaticWallpaperDbModel(
        dto.id,
        dto.image,
        dto.category,
        dto.ed,
        dto.date
    )

    fun mapCategoryDbModelToEntity(dbModel: CategoryDbModel) = Category(
        dbModel.id,
        dbModel.name,
        dbModel.image,
        dbModel.live,
        dbModel.date
    )

    fun mapStaticWallpaperDbModelToEntity(dbModel: StaticWallpaperDbModel) = StaticWallpaper(
        dbModel.id,
        dbModel.image,
        dbModel.category,
        dbModel.ed,
        dbModel.date
    )

}