package com.milovan.repodemo.data.network

import com.milovan.repodemo.data.network.details.ContributorNetwork
import com.milovan.repodemo.data.network.details.RepoDetailsNetwork
import com.milovan.repodemo.data.network.repos.RepoResponseNetwork

interface NetworkDataSource {
    suspend fun getRepositories(
        perPage: Int,
        page: Int
    ): RepoResponseNetwork

    suspend fun getRepoDetails(
        owner: String,
        name: String
    ): RepoDetailsNetwork

    suspend fun getContributors(
        url: String
    ): List<ContributorNetwork>
}