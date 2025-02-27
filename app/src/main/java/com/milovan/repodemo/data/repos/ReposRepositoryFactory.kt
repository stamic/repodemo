package com.milovan.repodemo.data.repos

import com.milovan.repodemo.data.repos.remote.RealNetworkDataSource
import com.milovan.repodemo.data.repos.remote.createGithubService

object ReposRepositoryFactory {
    fun createRepository(): ReposRepository {
        val api = createGithubService()
        val networkDataSource = RealNetworkDataSource(api)
        val repo = ReposRepositoryDefault(networkDataSource)
        return repo
    }
}