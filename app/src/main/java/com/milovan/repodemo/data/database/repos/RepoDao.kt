package com.milovan.repodemo.data.database.repos

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

    @Query("SELECT * from repo WHERE name LIKE :queryString OR" +
            " description LIKE :queryString " +
            "ORDER BY stars DESC, name ASC")
    fun searchByQueryPaged(queryString: String): PagingSource<Int, Repo>

    @Query(
        "SELECT * FROM repo WHERE " +
                "name LIKE :queryString OR description LIKE :queryString " +
                "ORDER BY stars DESC, name ASC"
    )
    suspend fun searchByQuery(queryString: String): List<Repo>

    @Query("SELECT * from repo ORDER BY stars DESC, name ASC")
    suspend fun getAll(): List<Repo>

    @Query("DELETE FROM repo")
    suspend fun deleteAll()
}