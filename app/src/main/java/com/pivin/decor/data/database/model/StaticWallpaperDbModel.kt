package com.pivin.decor.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "static_wallpaper")
data class StaticWallpaperDbModel(
    @PrimaryKey
    var id: Long = 0L,
    var image: String = "",
    var category: Long? = null,
    var ed: Boolean = false,
    var date: Long = 0L,
)