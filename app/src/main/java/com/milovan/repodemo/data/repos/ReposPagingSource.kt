package com.milovan.repodemo.data.repos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.milovan.repodemo.data.database.repos.Repo
import com.milovan.repodemo.data.database.repos.RepoDao
import com.milovan.repodemo.data.database.repos.toExternal
import com.milovan.repodemo.data.network.NetworkDataSource
import okio.IOException
import retrofit2.HttpException


class ReposPagingSource(
    private val networkDataSource: NetworkDataSource,
    private val dao: RepoDao
) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        try {
            val pageNumber = params.key ?: 1
            val prevKey = if (pageNumber > 1) {
                pageNumber - 1
            } else {
                null
            }
            val nextKey = pageNumber + 1

            val response = networkDataSource.getRepositories(params.loadSize, pageNumber)
            val repos = response.items.map { it.toExternal() }
            dao.insertAll(repos)

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
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}