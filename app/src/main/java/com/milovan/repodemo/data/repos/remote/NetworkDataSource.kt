package com.milovan.repodemo.data.repos.remote

import retrofit2.Response
import retrofit2.http.Query

interface NetworkDataSource {
    suspend fun getRepositories(
        perPage: Int,
        page: Int
    ): RepoResponseNetwork
}