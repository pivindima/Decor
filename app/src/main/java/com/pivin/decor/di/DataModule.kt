package com.pivin.decor.di

import android.app.Application
import com.pivin.decor.data.database.AppDatabase
import com.pivin.decor.data.database.WallpapersDao
import com.pivin.decor.data.network.ApiFactory
import com.pivin.decor.data.network.ApiService
import com.pivin.decor.data.repository.WallpapersRepositoryImpl
import com.pivin.decor.domain.model.WallpapersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindWallpapersRepository(impl: WallpapersRepositoryImpl): WallpapersRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideWallpapersDao(application: Application): WallpapersDao {
            return AppDatabase.getInstance(application).wallpapersDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}