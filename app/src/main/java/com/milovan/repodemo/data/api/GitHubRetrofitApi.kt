package com.milovan.repodemo.data.api
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

fun createGithubService(): GitHubRetrofitApi {
    val jsonNetwork = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    val networkApi = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(
            jsonNetwork.asConverterFactory("application/json".toMediaType()
            ))
        .build()
        .create(GitHubRetrofitApi::class.java)

    return networkApi
}

interface GitHubRetrofitApi {
    @GET("/search/repositories?q=language:kotlin&order=desc&sort=stars")
    suspend fun getRepos(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): RepoResponseNetwork

    @GET("/repos/{owner}/{name}")
    suspend fun getRepoDetails(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): RepoDetailsNetwork

    @GET
    suspend fun getContributors(
        @Url url: String
    ): List<ContributorNetwork>
}