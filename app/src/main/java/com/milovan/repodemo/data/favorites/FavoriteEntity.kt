package com.milovan.repodemo.data.favorites
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite"
)
data class FavoriteEntity(
    @PrimaryKey val id: String,
    val description: String
)