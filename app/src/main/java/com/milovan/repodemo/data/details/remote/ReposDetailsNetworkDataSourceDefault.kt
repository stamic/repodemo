package com.milovan.repodemo.data.details.remote

import com.milovan.repodemo.data.api.GitHubRetrofitApi
import com.milovan.repodemo.data.api.RepoDetailsNetwork

class ReposDetailsNetworkDataSourceDefault(
    val api: GitHubRetrofitApi
) : ReposDetailsNetworkDataSource {
    override suspend fun getRepoDetails(owner: String, name: String): RepoDetailsNetwork {
        return api.getRepoDetails(owner, name)
    }
}