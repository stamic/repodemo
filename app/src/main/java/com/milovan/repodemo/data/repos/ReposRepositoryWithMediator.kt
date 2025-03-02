package com.milovan.repodemo.data.repos

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android.codelabs.paging.data.GithubRemoteMediator
import com.milovan.repodemo.data.database.RepoDemoDatabase
import com.milovan.repodemo.data.database.repos.Repo
import com.milovan.repodemo.data.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReposRepositoryWithMediator @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val database: RepoDemoDatabase
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