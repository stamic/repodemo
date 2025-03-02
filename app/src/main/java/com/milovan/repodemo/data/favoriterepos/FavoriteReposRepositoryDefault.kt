package com.milovan.repodemo.data.favoriterepos

import com.milovan.repodemo.data.database.favoriterepos.FavoriteRepo
import com.milovan.repodemo.data.database.favoriterepos.FavoriteRepoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteReposRepositoryDefault @Inject constructor(
    private val dao: FavoriteRepoDao
) : FavoriteReposRepository {
    override suspend fun create(id: Long, name: String, avatarUrl: String) {
        val repo = FavoriteRepo(
            id,
            name,
            avatarUrl
        )
        dao.upsert(repo)
    }

    override fun getStream(): Flow<List<FavoriteRepo>> {
        return dao.observeAll().map { favs ->
            withContext(Dispatchers.Default) {
                favs.map { it.copy() }
            }
        }
    }

    override suspend fun getAll(): List<FavoriteRepo> {
        return dao.getAll()
    }

    override suspend fun getById(id: Long): FavoriteRepo? {
        return dao.getById(id)
    }

    override suspend fun deleteById(id: Long) {
        dao.deleteById(id)
    }
}