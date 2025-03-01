package com.milovan.repodemo.data.repos.remote

import com.milovan.repodemo.data.api.RepoResponseNetwork

interface ReposNetworkDataSource {
    suspend fun getRepositories(
        perPage: Int,
        page: Int
    ): RepoResponseNetwork
}