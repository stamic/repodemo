package com.milovan.repodemo.data.details

import com.milovan.repodemo.data.api.createGithubService
import com.milovan.repodemo.data.details.remote.RepoDetailsNetworkDataSourceDefault

object DetailsFactory {
    fun create(): RepoDetailsRepository {
        val api = createGithubService()
        val networkDataSource = RepoDetailsNetworkDataSourceDefault(api)
        val repository = RepoDetailsRepositoryDefault(networkDataSource)
        return repository
    }
}