package com.milovan.repodemo.data.database.favoriterepos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite_repo"
)
data class FavoriteRepo(
    @PrimaryKey var id: Long,
    var name: String,
    var avatarUrl: String
)