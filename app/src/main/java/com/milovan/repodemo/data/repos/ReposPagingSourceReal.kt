package com.milovan.repodemo.data.repos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.milovan.repodemo.data.repos.remote.NetworkDataSource
import okio.IOException
import retrofit2.HttpException
import kotlin.math.max

private const val STARTING_KEY = 0

class ReposPagingSourceReal(
    private val networkDataSource: NetworkDataSource
) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        try {
            val startKey = params.key ?: STARTING_KEY
            val pageNumber = startKey / params.loadSize + 1
            val nextKey = startKey + params.loadSize

            val prevKey: Int? = if (startKey - params.loadSize <= STARTING_KEY) {
                null
            } else {
                startKey - params.loadSize
            }

            val response = networkDataSource.getRepositories(params.loadSize, pageNumber)
            val repos = if (response.items != null) {
                response.items.map { it.toExternal() }
            } else {
                listOf()
            }

            return LoadResult.Page(
                repos,
                prevKey,
                nextKey
            )

        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        val anchorPosition = state.anchorPosition ?: 0
        var refreshKey = anchorPosition - state.config.initialLoadSize / 2
        refreshKey = (refreshKey / state.config.pageSize - 1) * state.config.pageSize
        refreshKey = max(0, refreshKey)
        return refreshKey
    }

}