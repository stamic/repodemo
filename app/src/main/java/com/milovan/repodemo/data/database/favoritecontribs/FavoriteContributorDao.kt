package com.milovan.repodemo.data.database.favoritecontribs

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteContributorDao {
    @Query("SELECT * FROM favorite_contributor")
    fun observeAll(): Flow<List<FavoriteContributor>>

    @Query("SELECT * FROM favorite_contributor")
    suspend fun getAll(): List<FavoriteContributor>

    @Query("SELECT * FROM favorite_contributor WHERE id = :id")
    suspend fun getById(id: Long): FavoriteContributor?

    @Upsert
    suspend fun upsert(favoriteContributor: FavoriteContributor)

    @Query("DELETE FROM favorite_contributor WHERE id = :id")
    suspend fun deleteById(id: Long): Int
}