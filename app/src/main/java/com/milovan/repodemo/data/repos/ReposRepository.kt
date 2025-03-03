package com.milovan.repodemo.data.repos

import androidx.paging.PagingData
import com.milovan.repodemo.data.database.repos.Repo
import kotlinx.coroutines.flow.Flow

interface ReposRepository {
    fun getReposStream(): Flow<PagingData<Repo>>
    fun getReposSearchStream(query: String): Flow<PagingData<Repo>>
    suspend fun searchByQuery(queryString: String): List<Repo>
}