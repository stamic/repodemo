package com.milovan.repodemo.data.repos

import androidx.paging.PagingSource

class ReposRepositoryFake : ReposRepository {
    override fun reposPagingSource(): PagingSource<Int, Repo> {
        return ReposPagingSourceFake()
    }
}