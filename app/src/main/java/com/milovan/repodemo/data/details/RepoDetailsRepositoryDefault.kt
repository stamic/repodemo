package com.milovan.repodemo.data.details

import com.milovan.repodemo.data.details.remote.RepoDetailsNetworkDataSource

class RepoDetailsRepositoryDefault(
    private val networkDataSource: RepoDetailsNetworkDataSource
) : RepoDetailsRepository {
    override suspend fun getRepoDetails(owner: String, name: String): RepoDetails {
        val detailsNetwork = networkDataSource.getRepoDetails(owner, name)
        return detailsNetwork.toExternal()
    }

    override suspend fun getContributors(contributorsUrl: String): List<Contributor> {
        val contributorsNetwork = networkDataSource.getContributors(contributorsUrl)
        return contributorsNetwork.map { it.toExternal() }
    }
}