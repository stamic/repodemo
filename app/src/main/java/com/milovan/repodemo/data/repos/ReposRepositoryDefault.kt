package com.milovan.repodemo.data.repos

import androidx.paging.PagingSource
import com.milovan.repodemo.data.repos.remote.NetworkDataSource

class ReposRepositoryDefault(
    private val networkDataSource: NetworkDataSource
) : ReposRepository {
    override fun reposPagingSource(): PagingSource<Int, Repo> {
        return ReposPagingSourceReal(networkDataSource)
    }
}