package com.milovan.repodemo.data.repos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.milovan.repodemo.data.database.repos.Repo
import kotlinx.coroutines.delay
import kotlin.math.max

private const val STARTING_KEY = 0
private const val LOAD_DELAY_MILLIS = 3_000L


class ReposPagingSourceFake : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        // If params.key is null, it is the first load, so we start loading with STARTING_KEY
        val startKey = params.key ?: STARTING_KEY

        // We fetch as many articles as hinted to by params.loadSize
        val range = startKey.until(startKey + params.loadSize)

        val prevKey = when (startKey) {
            STARTING_KEY -> null
            else -> when (val prevKey = ensureValidKey(key = range.first - params.loadSize)) {
                STARTING_KEY -> null
                else -> prevKey
            }
        }

        val nextKey = range.last + 1

        // Simulate a delay for loads adter the initial load
        if (startKey != STARTING_KEY) delay(LOAD_DELAY_MILLIS)
        val repos = range.map { number ->
            Repo(
                id = number.toLong(),
                name = "Repo $number",
                description = "This describes repo $number"
            )
        }

        return LoadResult.Page(
            repos,
            prevKey,
            nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val repo = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = repo.id.toInt() - (state.config.pageSize / 2))
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}
