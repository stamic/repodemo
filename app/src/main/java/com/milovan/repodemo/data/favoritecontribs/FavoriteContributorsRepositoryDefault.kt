package com.milovan.repodemo.data.favoritecontribs

import com.milovan.repodemo.data.database.favoritecontribs.FavoriteContributor
import com.milovan.repodemo.data.database.favoritecontribs.FavoriteContributorDao
import javax.inject.Inject

class FavoriteContributorsRepositoryDefault @Inject constructor(
    private val dao: FavoriteContributorDao
) : FavoriteContributorsRepository {
    override suspend fun create(id: Long, login: String, avatarUrl: String) {
        val contributor = FavoriteContributor(
            id,
            login,
            avatarUrl
        )
        dao.upsert(contributor)
    }

    override suspend fun getAll(): List<FavoriteContributor> {
        return dao.getAll()
    }

    override suspend fun getById(id: Long): FavoriteContributor? {
        return dao.getById(id)
    }

    override suspend fun deleteById(id: Long) {
        dao.deleteById(id)
    }
}