package com.milovan.repodemo.data.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.milovan.repodemo.data.repos.local.Repo
import com.milovan.repodemo.data.repos.remote.NetworkDataSource
import kotlinx.coroutines.flow.Flow

class ReposRepositorySimpleOnline(
    private val networkDataSource: NetworkDataSource
) : ReposRepository {

    override fun getReposStream(): Flow<PagingData<Repo>> {
        val reposStream: Flow<PagingData<Repo>> = Pager(
            config = PagingConfig(
                pageSize = Consts.ITEMS_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ReposPagingSourceReal(networkDataSource) }
        ).flow

        return reposStream
    }
}