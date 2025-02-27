package com.milovan.repodemo.data.repos

import androidx.paging.PagingSource

interface ReposRepository {
    fun reposPagingSource(): PagingSource<Int, Repo>
}