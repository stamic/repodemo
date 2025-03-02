package com.milovan.repodemo.data.favoriterepos

import com.milovan.repodemo.data.database.favoriterepos.FavoriteRepo
import kotlinx.coroutines.flow.Flow

interface FavoriteReposRepository {
    suspend fun create(id: Long, name: String, avatarUrl: String)
    fun getStream(): Flow<List<FavoriteRepo>>
    suspend fun getAll(): List<FavoriteRepo>
    suspend fun getById(id: Long): FavoriteRepo?
    suspend fun deleteById(id: Long)
}