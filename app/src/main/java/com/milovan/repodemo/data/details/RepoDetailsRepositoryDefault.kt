package com.milovan.repodemo.data.details

import com.milovan.repodemo.data.network.NetworkDataSource
import javax.inject.Inject


class RepoDetailsRepositoryDefault @Inject constructor(
    private val networkDataSource: NetworkDataSource
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