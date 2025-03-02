package com.milovan.repodemo.data.favoritecontribs

import com.milovan.repodemo.data.database.favoritecontribs.FavoriteContributor

interface FavoriteContributorsRepository {
    suspend fun create(id: Long, login: String, avatarUrl: String)
    suspend fun getAll(): List<FavoriteContributor>
    suspend fun getById(id: Long): FavoriteContributor?
    suspend fun deleteById(id: Long)
}