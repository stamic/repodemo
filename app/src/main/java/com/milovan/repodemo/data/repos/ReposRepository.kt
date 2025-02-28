package com.milovan.repodemo.data.repos

import androidx.paging.PagingData
import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow

interface ReposRepository {
    fun getReposStream(): Flow<PagingData<Repo>>
}