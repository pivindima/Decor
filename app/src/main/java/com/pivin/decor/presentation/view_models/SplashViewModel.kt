package com.pivin.decor.presentation.view_models

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pivin.decor.data.database.WallpapersDao
import com.pivin.decor.data.mapper.Mapper
import com.pivin.decor.data.network.ApiFactory
import com.pivin.decor.utils.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val wallpapersDao: WallpapersDao,
    private val mapper: Mapper,
    private val app: Application
) : ViewModel() {

    private var countAction = AtomicInteger()

    val liveData: LiveData<SplashState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()

    fun checkVersionDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val versionDb = Preferences.getLong(app, Preferences.VERSION_DB)
            ApiFactory.apiService.getSettings().first {
                it.name == Preferences.VERSION_DB
            }.let {
                if (versionDb < it.value) updateDb(it.value)
            }
            startMainActivity()
        }
    }

    private suspend fun updateDb(newVersionDb: Long) {
        val categories = ApiFactory.apiService.getCategories().map {
            mapper.mapCategoryDtoToDbModel(it)
        }
        val staticWallpapers = ApiFactory.apiService.getStaticWallpapers().map {
            mapper.mapStaticWallpaperDtoToDbModel(it)
        }

        wallpapersDao.insertCategories(categories)
        wallpapersDao.insertStaticWallpapers(staticWallpapers)

        Preferences.putLong(app, Preferences.VERSION_DB, newVersionDb)

        Log.i(SplashViewModel::class.java.name, "Database updated version: $newVersionDb")

    }

    fun animationFinished() {
        startMainActivity()
    }

    private fun startMainActivity() {
        synchronized(countAction) {
            if (countAction.incrementAndGet() >= START_MAIN_ACTIVITY_FLAG) {
                mutableLiveData.postValue(SplashState.StartMain)
            }
        }
    }

    companion object {
        private const val START_MAIN_ACTIVITY_FLAG = 2
    }

}

sealed class SplashState {
    object StartMain : SplashState()
}