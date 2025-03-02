package com.milovan.repodemo.data.details

interface RepoDetailsRepository {
    suspend fun getRepoDetails(owner: String, name: String): RepoDetails
    suspend fun getContributors(contributorsUrl: String): List<Contributor>
}