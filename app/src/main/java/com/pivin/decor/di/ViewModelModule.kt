package com.pivin.decor.di

import androidx.lifecycle.ViewModel
import com.pivin.decor.presentation.view_models.CategoryViewModel
import com.pivin.decor.presentation.view_models.LiveViewModel
import com.pivin.decor.presentation.view_models.SplashViewModel
import com.pivin.decor.presentation.view_models.StaticViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    fun bindCategoryViewModel(viewModel: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StaticViewModel::class)
    fun bindStaticViewModel(viewModel: StaticViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LiveViewModel::class)
    fun bindLiveViewModel(viewModel: LiveViewModel): ViewModel
}