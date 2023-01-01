package com.pivin.decor.domain.model

import com.pivin.decor.data.network.ApiFactory

data class StaticWallpaper(
    var id: Long = 0L,
    var image: String = "",
    var category: Long? = null,
    var ed: Boolean = false,
    var date: Long = 0L,
) {
    val urlThumb
        get() = "${ApiFactory.BASE_URL}/uploads/decor/static/small/$image"

    val urlPreview
        get() = "${ApiFactory.BASE_URL}/uploads/decor/static/uhd/$image"
}
