package com.milovan.repodemo.data.favorites

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    suspend fun getAll(): List<FavoriteEntity>

    @Upsert
    suspend fun upsert(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE id = :favoriteId")
    suspend fun deleteById(favoriteId: String): Int
}