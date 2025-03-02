package com.milovan.repodemo.data.favoritecontribs

import com.milovan.repodemo.data.database.favoritecontribs.FavoriteContributor

interface FavoriteContributorsRepository {
    suspend fun create(login: String, avatarUrl: String)
    suspend fun getAll(): List<FavoriteContributor>
    suspend fun getByLoginName(name: String): FavoriteContributor?
    suspend fun deleteByLoginName(login: String)
}