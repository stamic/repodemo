package com.milovan.repodemo.data.repos

interface ReposRepository {
    fun reposPagingSource(): ReposPagingSource
}