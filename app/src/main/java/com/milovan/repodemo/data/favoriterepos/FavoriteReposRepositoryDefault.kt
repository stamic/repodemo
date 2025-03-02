package com.milovan.repodemo.data.favoriterepos

import com.milovan.repodemo.data.database.favoriterepos.FavoriteRepo
import com.milovan.repodemo.data.database.favoriterepos.FavoriteRepoDao
import javax.inject.Inject

class FavoriteReposRepositoryDefault @Inject constructor(
    private val dao: FavoriteRepoDao
) : FavoriteReposRepository {
    override suspend fun create(id: Long, login: String, avatarUrl: String) {
        val contributor = FavoriteRepo(
            id,
            login,
            avatarUrl
        )
        dao.upsert(contributor)
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