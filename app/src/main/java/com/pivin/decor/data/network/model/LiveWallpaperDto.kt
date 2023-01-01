package com.pivin.decor.data.network.model

import com.google.gson.annotations.Expose

data class LiveWallpaperDto(
    @Expose
    var id: Long = 0L,
    @Expose
    var image: String = "",
    @Expose
    var category: Long? = null,
    @Expose
    var ed: Boolean = false,
    @Expose
    var date: Long = 0L,
)
