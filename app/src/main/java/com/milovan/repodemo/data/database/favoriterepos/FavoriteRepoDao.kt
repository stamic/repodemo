package com.milovan.repodemo.data.database.favoriterepos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRepoDao {
    @Query("SELECT * FROM favorite_repo")
    fun observeAll(): Flow<List<FavoriteRepo>>

    @Query("SELECT * FROM favorite_repo")
    suspend fun getAll(): List<FavoriteRepo>

    @Query("SELECT * FROM favorite_repo WHERE id = :id")
    suspend fun getById(id: Long): FavoriteRepo?

    @Upsert
    suspend fun upsert(favoriteContributor: FavoriteRepo)

    @Query("DELETE FROM favorite_repo WHERE id = :id")
    suspend fun deleteById(id: Long): Int
}