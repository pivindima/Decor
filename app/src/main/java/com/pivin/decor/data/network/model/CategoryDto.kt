package com.pivin.decor.data.network.model

import com.google.gson.annotations.Expose

data class CategoryDto(
    @Expose
    var id: Long = 0L,
    @Expose
    var name: String = "",
    @Expose
    var image: String = "",
    @Expose
    var live: Boolean = false,
    @Expose
    var date: Long = 0L,
)
