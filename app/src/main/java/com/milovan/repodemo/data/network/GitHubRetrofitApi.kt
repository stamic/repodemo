package com.milovan.repodemo.data.network
import com.milovan.repodemo.data.network.details.ContributorNetwork
import com.milovan.repodemo.data.network.details.RepoDetailsNetwork
import com.milovan.repodemo.data.network.repos.RepoResponseNetwork
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

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