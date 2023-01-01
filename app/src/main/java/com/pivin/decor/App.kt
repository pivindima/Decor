package com.pivin.decor

import android.app.Application
import com.pivin.decor.di.DaggerApplicationComponent

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

}