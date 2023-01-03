package com.pivin.decor.data.network

import com.pivin.decor.data.network.model.CategoryDto
import com.pivin.decor.data.network.model.SettingsDto
import com.pivin.decor.data.network.model.StaticWallpaperDto
import retrofit2.http.GET

interface ApiService {

    @GET("settings")
    suspend fun getSettings(): ArrayList<SettingsDto>

    @GET("categories")
    suspend fun getCategories(): ArrayList<CategoryDto>

    @GET("static")
    suspend fun getStaticWallpapers(): ArrayList<StaticWallpaperDto>

}