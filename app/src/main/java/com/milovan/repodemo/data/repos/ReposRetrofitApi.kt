package com.milovan.repodemo.data.repos
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesRetrofitApi {
    @GET("/search/repositories?q=languate:kotlin&order=desc&sort=stars")
    suspend fun getRepositories(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): PagedRepositoriesNetwork

    @GET("/search/repositories?q=language:kotlin&order=desc&sort=stars&per_page=20&page=1")
    suspend fun getRepositoriesTest(): PagedRepositoriesNetwork
}