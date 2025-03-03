package com.milovan.repodemo.data.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.milovan.repodemo.data.database.repos.Repo
import kotlinx.coroutines.flow.Flow

class ReposRepositoryFake : ReposRepository {
    override fun getReposStream(): Flow<PagingData<Repo>> {
        val reposStream: Flow<PagingData<Repo>> = Pager(
            config = PagingConfig(
                pageSize = Consts.ITEMS_PER_PAGE,
                initialLoadSize = Consts.ITEMS_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ReposPagingSourceFake() }
        ).flow

        return reposStream
    }

    override fun getReposSearchStream(query: String): Flow<PagingData<Repo>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchByQuery(queryString: String): List<Repo> {
        TODO("Not yet implemented")
    }
}