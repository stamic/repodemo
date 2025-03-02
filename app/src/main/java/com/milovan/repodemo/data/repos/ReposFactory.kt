package com.milovan.repodemo.data.repos

import android.content.Context
import com.milovan.repodemo.data.repos.local.RepoDatabase
import com.milovan.repodemo.data.repos.remote.RealNetworkDataSourceDefault
import com.milovan.repodemo.data.api.createGithubService

object ReposFactory {
    fun createWithoutDatabase(): ReposRepository {
        val api = createGithubService()
        val networkDataSource = RealNetworkDataSourceDefault(api)
        val repo = ReposRepositorySimpleOnline(networkDataSource)
        return repo
    }

    fun createWithMediator(context: Context): ReposRepository {
        val api = createGithubService()
        val networkDataSource = RealNetworkDataSourceDefault(api)
        val database = RepoDatabase.getInstance(context)
        val repo = ReposRepositoryWithMediator(networkDataSource, database)
        return repo
    }
}