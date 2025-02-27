package com.milovan.repodemo.data.repos.remote

class RealNetworkDataSource(
    val api: GitHubRetrofitApi
) : NetworkDataSource {
    override suspend fun getRepositories(perPage: Int, page: Int): RepoResponseNetwork {
        return api.getRepositories(perPage, page)
    }
}