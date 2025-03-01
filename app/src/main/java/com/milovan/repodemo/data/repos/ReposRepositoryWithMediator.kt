package com.milovan.repodemo.data.repos

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android.codelabs.paging.data.GithubRemoteMediator
import com.milovan.repodemo.data.repos.local.Repo
import com.milovan.repodemo.data.repos.local.RepoDatabase
import com.milovan.repodemo.data.repos.remote.ReposNetworkDataSource
import kotlinx.coroutines.flow.Flow

class ReposRepositoryWithMediator(
    private val networkDataSource: ReposNetworkDataSource,
    private val database: RepoDatabase
) : ReposRepository {
    override fun getReposStream(): Flow<PagingData<Repo>> {
        @OptIn(ExperimentalPagingApi::class)
        val reposStream: Flow<PagingData<Repo>> = Pager(
            config = PagingConfig(
                pageSize = Consts.ITEMS_PER_PAGE,
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