package com.milovan.repodemo.data.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.milovan.repodemo.data.database.repos.Repo
import com.milovan.repodemo.data.database.repos.RepoDao
import com.milovan.repodemo.data.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class ReposRepositorySimpleOnline @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val dao: RepoDao
) : ReposRepository {

    override fun getReposStream(): Flow<PagingData<Repo>> {
        val reposStream: Flow<PagingData<Repo>> = Pager(
            config = PagingConfig(
                pageSize = Consts.ITEMS_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ReposPagingSource(networkDataSource, dao) }
        ).flow

        return reposStream
    }

    override fun getReposSearchStream(query: String): Flow<PagingData<Repo>> {
        val dbQuery = "%${query.replace(' ', '%')}%"
        val reposStream: Flow<PagingData<Repo>> = Pager(
            config = PagingConfig(
                pageSize = Consts.ITEMS_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { dao.searchByQueryPaged(dbQuery) }
        ).flow.map { page ->
            page.map { repo ->
                Timber.d("${repo.name}")
                repo.copy()
            }
        }

        return reposStream
    }

    override suspend fun searchByQuery(queryString: String): List<Repo> {
        val dbQuery = "%${queryString.replace(' ', '%')}%"
        return dao.searchByQuery(dbQuery)
    }
}