package com.milovan.repodemo.data.database.favoritecontribs

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite_contributor"
)
data class FavoriteContributor(
    @PrimaryKey var id: Long,
    var login: String,
    var avatarUrl: String
)