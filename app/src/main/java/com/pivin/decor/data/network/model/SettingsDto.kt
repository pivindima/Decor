package com.pivin.decor.data.network.model

import com.google.gson.annotations.Expose

data class SettingsDto(
    @Expose
    var name: String = "",
    @Expose
    var value: Long = 0L,
)
