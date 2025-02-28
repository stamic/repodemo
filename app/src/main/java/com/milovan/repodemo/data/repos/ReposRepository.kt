package com.milovan.repodemo.data.repos

import androidx.paging.PagingData
import com.milovan.repodemo.data.repos.local.Repo
import kotlinx.coroutines.flow.Flow

interface ReposRepository {
    fun getReposStream(): Flow<PagingData<Repo>>
}