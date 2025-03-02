package com.milovan.repodemo.data.favorites

import com.milovan.repodemo.data.database.favoritecontribs.FavoriteContributor
import com.milovan.repodemo.data.database.favoritecontribs.FavoriteContributorDao
import javax.inject.Inject

class FavoriteContributorsRepositoryDefault @Inject constructor(
    private val dao: FavoriteContributorDao
) : FavoriteContributorsRepository {
    override suspend fun create(login: String, avatarUrl: String) {
        val contributor = FavoriteContributor(
            login,
            avatarUrl
        )
        dao.upsert(contributor)
    }

    override suspend fun getAll(): List<FavoriteContributor> {
        return dao.getAll()
    }

    override suspend fun deleteByLogin(login: String) {
        dao.deleteByLoginName(login)
    }
}