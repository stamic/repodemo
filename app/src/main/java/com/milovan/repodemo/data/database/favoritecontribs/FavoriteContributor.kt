package com.milovan.repodemo.data.database.favoritecontribs
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(
    tableName = "favorite_contributor"
)
data class FavoriteContributor(
    @PrimaryKey var login: String,
    var avatarUrl: String
)