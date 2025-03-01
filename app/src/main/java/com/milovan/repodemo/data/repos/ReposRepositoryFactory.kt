package com.milovan.repodemo.data.repos

import android.content.Context
import com.milovan.repodemo.data.repos.local.RepoDatabase
import com.milovan.repodemo.data.repos.remote.RealNetworkDataSource
import com.milovan.repodemo.data.repos.remote.createGithubService

object ReposRepositoryFactory {
    fun createRepoWithoutDatabase(): ReposRepository {
        val api = createGithubService()
        val networkDataSource = RealNetworkDataSource(api)
        val repo = ReposRepositorySimpleOnline(networkDataSource)
        return repo
    }

    fun createRepoWithMediator(context: Context): ReposRepository {
        val api = createGithubService()
        val networkDataSource = RealNetworkDataSource(api)
        val database = RepoDatabase.getInstance(context)
        val repo = ReposRepositoryWithMediator(networkDataSource, database)
        return repo
    }
}