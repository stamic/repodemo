package com.milovan.repodemo.data.details.remote

import com.milovan.repodemo.data.api.RepoDetailsNetwork

interface ReposDetailsNetworkDataSource {
    suspend fun getRepoDetails(
        owner: String,
        name: String
    ): RepoDetailsNetwork
}