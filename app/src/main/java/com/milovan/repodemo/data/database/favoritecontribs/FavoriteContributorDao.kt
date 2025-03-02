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

    @Query("SELECT * FROM favorite_contributor WHERE login = :name")
    suspend fun getByLoginName(name: String): FavoriteContributor?

    @Upsert
    suspend fun upsert(favoriteContributor: FavoriteContributor)

    @Query("DELETE FROM favorite_contributor WHERE login = :name")
    suspend fun deleteByLoginName(name: String): Int
}