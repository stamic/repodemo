package com.milovan.repodemo.data.repos

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android.codelabs.paging.data.GithubRemoteMediator
import com.milovan.repodemo.data.repos.local.Repo
import com.milovan.repodemo.data.repos.local.RepoDatabase
import com.milovan.repodemo.data.repos.remote.NetworkDataSource
import kotlinx.coroutines.flow.Flow

private const val ITEMS_PER_PAGE = 50

class ReposRepositoryWithMediator(
    private val networkDataSource: NetworkDataSource,
    private val database: RepoDatabase
) : ReposRepository {
    override fun getReposStream(): Flow<PagingData<Repo>> {
        @OptIn(ExperimentalPagingApi::class)
        val reposStream: Flow<PagingData<Repo>> = Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
                enablePlaceholders = false
            ),
            remoteMediator = GithubRemoteMediator(
                networkDataSource,
                database
            ),
            pagingSourceFactory = {database.reposDao().pagingSource()}
        ).flow

        return reposStream
    }
}