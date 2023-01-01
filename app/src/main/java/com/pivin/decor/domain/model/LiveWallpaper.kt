package com.pivin.decor.domain.model

import com.pivin.decor.data.network.ApiFactory

data class LiveWallpaper(
    var id: Long = 0L,
    var image: String = "",
    var category: Long? = null,
    var ed: Boolean = false,
    var date: Long = 0L,
) {
    val urlThumb
        get() = "${ApiFactory.BASE_URL}/uploads/decor/live/thumb/$image.jpg"

    val urlPreview
        get() = "${ApiFactory.BASE_URL}/uploads/decor/live/720p/$image"
}
