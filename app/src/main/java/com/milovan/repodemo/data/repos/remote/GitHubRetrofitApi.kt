package com.milovan.repodemo.data.repos.remote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesRetrofitApi {
    @GET("/search/repositories?q=language:kotlin&order=desc&sort=stars")
    suspend fun getRepositories(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<RepoResponsNetwork>

    @GET("/search/repositories?q=language:kotlin&order=desc&sort=stars&per_page=20&page=1")
    suspend fun getRepositoriesTest(): Response<RepoResponsNetwork>
}