package com.milovan.repodemo.data.favoriterepos

import com.milovan.repodemo.data.database.favoriterepos.FavoriteRepo

interface FavoriteReposRepository {
    suspend fun create(id: Long, login: String, avatarUrl: String)
    suspend fun getAll(): List<FavoriteRepo>
    suspend fun getById(id: Long): FavoriteRepo?
    suspend fun deleteById(id: Long)
}