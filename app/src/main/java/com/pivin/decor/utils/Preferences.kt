package com.pivin.decor.utils

import android.content.Context

class Preferences {
    companion object {

        private const val PREFERENCES = "PREFERENCES"
        const val VERSION_DB = "VERSION_DB"

        fun getLong(context: Context, key: String) = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE).getLong(key, 0)
        fun getBoolean(context: Context, key: String) = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE).getBoolean(key, false)

        fun putLong(context: Context, key: String, value: Long) {
            context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE).edit().putLong(key, value).apply()
        }

        fun putBoolean(context: Context, key: String, value: Boolean) {
            context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE).edit().putBoolean(key, value).apply()
        }

    }
}