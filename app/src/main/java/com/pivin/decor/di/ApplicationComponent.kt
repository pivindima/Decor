package com.pivin.decor.di

import android.app.Application
import com.pivin.decor.App
import com.pivin.decor.presentation.activity.CategoryLiveActivity
import com.pivin.decor.presentation.activity.CategoryStaticActivity
import com.pivin.decor.presentation.activity.LiveActivity
import com.pivin.decor.presentation.fragment.CategoriesFragment
import com.pivin.decor.presentation.activity.MainActivity
import com.pivin.decor.presentation.activity.SplashActivity
import com.pivin.decor.presentation.activity.StaticActivity
import com.pivin.decor.presentation.fragment.LiveFragment
import com.pivin.decor.presentation.fragment.StaticFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: SplashActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(categoriesFragment: CategoriesFragment)

    fun inject(staticFragment: StaticFragment)

    fun inject(liveFragment: LiveFragment)

    fun inject(categoryLiveActivity: CategoryLiveActivity)

    fun inject(categoryStaticActivity: CategoryStaticActivity)

    fun inject(staticActivity: StaticActivity)

    fun inject(liveActivity: LiveActivity)

    fun inject(application: App)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}