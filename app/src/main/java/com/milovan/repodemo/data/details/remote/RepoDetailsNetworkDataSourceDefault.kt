package com.milovan.repodemo.data.details.remote

import com.milovan.repodemo.data.api.ContributorNetwork
import com.milovan.repodemo.data.api.GitHubRetrofitApi
import com.milovan.repodemo.data.api.RepoDetailsNetwork

class RepoDetailsNetworkDataSourceDefault(
    val api: GitHubRetrofitApi
) : RepoDetailsNetworkDataSource {
    override suspend fun getRepoDetails(owner: String, name: String): RepoDetailsNetwork {
        return api.getRepoDetails(owner, name)
    }

    override suspend fun getContributors(url: String): List<ContributorNetwork> {
        return api.getContributors(url)
    }
}