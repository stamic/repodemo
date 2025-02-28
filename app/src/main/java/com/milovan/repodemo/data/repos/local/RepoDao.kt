package com.milovan.repodemo.data.repos.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<Repo>)

    @Query("SELECT * from repo ORDER BY stars DESC, name ASC")
    fun pagingSource(): PagingSource<Int, Repo>

    @Query("DELETE FROM repo")
    suspend fun deleteAll()
}