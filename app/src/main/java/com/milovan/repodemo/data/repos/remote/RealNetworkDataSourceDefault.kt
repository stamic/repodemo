package com.milovan.repodemo.data.repos.remote

import com.milovan.repodemo.data.api.GitHubRetrofitApi
import com.milovan.repodemo.data.api.RepoResponseNetwork

class RealNetworkDataSourceDefault(
    val api: GitHubRetrofitApi
) : ReposNetworkDataSource {
    override suspend fun getRepositories(perPage: Int, page: Int): RepoResponseNetwork {
        return api.getRepos(perPage, page)
    }
}