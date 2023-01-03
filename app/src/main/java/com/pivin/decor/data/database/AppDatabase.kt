package com.pivin.decor.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pivin.decor.data.database.model.CategoryDbModel
import com.pivin.decor.data.database.model.StaticWallpaperDbModel

@Database(entities = [CategoryDbModel::class, StaticWallpaperDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "decor"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun wallpapersDao(): WallpapersDao
}