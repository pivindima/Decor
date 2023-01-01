package com.pivin.decor.domain.model

import com.pivin.decor.data.network.ApiFactory

data class Category(
    var id: Long = 0L,
    var name: String = "",
    var image: String = "",
    var live: Boolean = false,
    var date: Long = 0L,
) {
    val imageUrl
        get() = "${ApiFactory.BASE_URL}/uploads/decor/categories/$image"
}
