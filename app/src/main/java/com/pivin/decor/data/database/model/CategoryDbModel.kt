package com.pivin.decor.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryDbModel(
    @PrimaryKey
    var id: Long = 0L,
    var name: String = "",
    var image: String = "",
    var live: Boolean = false,
    var date: Long = 0L,
)