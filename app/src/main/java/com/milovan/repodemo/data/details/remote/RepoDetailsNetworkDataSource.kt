package com.milovan.repodemo.data.details.remote

import com.milovan.repodemo.data.api.ContributorNetwork
import com.milovan.repodemo.data.api.RepoDetailsNetwork

interface RepoDetailsNetworkDataSource {
    suspend fun getRepoDetails(
        owner: String,
        name: String
    ): RepoDetailsNetwork

    suspend fun getContributors(
        url: String
    ): List<ContributorNetwork>
}