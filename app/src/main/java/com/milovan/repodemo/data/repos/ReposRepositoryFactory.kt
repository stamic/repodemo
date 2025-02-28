package com.milovan.repodemo.data.repos

import android.content.Context
import com.milovan.repodemo.data.repos.local.RepoDatabase
import com.milovan.repodemo.data.repos.remote.RealNetworkDataSource
import com.milovan.repodemo.data.repos.remote.createGithubService

object ReposRepositoryFactory {
    fun createRepository(): ReposRepository {
        val api = createGithubService()
        val networkDataSource = RealNetworkDataSource(api)
        val repo = ReposRepositoryDefault(networkDataSource)
        return repo
    }

    fun createOfflineRepository(context: Context): ReposRepository {
        val api = createGithubService()
        val networkDataSource = RealNetworkDataSource(api)
        val database = RepoDatabase.getInstance(context)
        val repo = ReposRepositoryOffline(networkDataSource, database)
        return repo
    }
}