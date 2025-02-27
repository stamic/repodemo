package com.milovan.repodemo.data.repos

class ReposRepositoryDefault() : ReposRepository {
    override fun reposPagingSource(): ReposPagingSource {
        return ReposPagingSource()
    }
}