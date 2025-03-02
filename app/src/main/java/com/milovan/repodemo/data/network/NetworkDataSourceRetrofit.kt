package com.milovan.repodemo.data.network

import com.milovan.repodemo.data.network.details.ContributorNetwork
import com.milovan.repodemo.data.network.details.RepoDetailsNetwork
import com.milovan.repodemo.data.network.repos.RepoResponseNetwork
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkDataSourceRetrofit @Inject constructor(): NetworkDataSource {
    val api: GitHubRetrofitApi

    init {
        val jsonNetwork = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        api = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(
                jsonNetwork.asConverterFactory("application/json".toMediaType()
                ))
            .build()
            .create(GitHubRetrofitApi::class.java)
    }

    override suspend fun getRepositories(perPage: Int, page: Int): RepoResponseNetwork {
        return api.getRepos(perPage, page)
    }

    override suspend fun getRepoDetails(owner: String, name: String): RepoDetailsNetwork {
        return api.getRepoDetails(owner, name)
    }

    override suspend fun getContributors(url: String): List<ContributorNetwork> {
        return api.getContributors(url)
    }
}