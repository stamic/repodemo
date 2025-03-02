package com.milovan.repodemo.data.favorites

import com.milovan.repodemo.data.database.favoritecontribs.FavoriteContributor

interface FavoriteContributorsRepository {
    suspend fun create(login: String, avatarUrl: String)
    suspend fun getAll(): List<FavoriteContributor>
    suspend fun deleteByLogin(login: String)
}