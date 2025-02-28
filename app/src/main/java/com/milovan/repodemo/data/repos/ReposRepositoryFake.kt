package com.milovan.repodemo.data.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.milovan.repodemo.data.repos.local.Repo
import kotlinx.coroutines.flow.Flow

private const val ITEMS_PER_PAGE = 50

class ReposRepositoryFake : ReposRepository {
    override fun getReposStream(): Flow<PagingData<Repo>> {
        val reposStream: Flow<PagingData<Repo>> = Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
                initialLoadSize = ITEMS_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ReposPagingSourceFake() }
        ).flow

        return reposStream
    }
}