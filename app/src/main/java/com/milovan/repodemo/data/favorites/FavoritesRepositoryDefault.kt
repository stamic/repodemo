package com.milovan.repodemo.data.favorites

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

class FavoritesRepositoryDefault(
    private val localDataSource: FavoriteDao,
    private val dispatcher: CoroutineDispatcher,
    private val scope: CoroutineScope,
) : FavoritesRepository {
    override suspend fun createFavorite(id: String, description: String) {
        val favorite = Favorite(
            id,
            description
        )

        localDataSource.upsert(favorite.toEntity())
    }

    override suspend fun getFavorites(): List<Favorite> {
        val result = localDataSource.getAll()
        return result.map { it.toExternal() }
    }

    override suspend fun deleteFavorite(id: String) {
        localDataSource.deleteById(id)
    }
}