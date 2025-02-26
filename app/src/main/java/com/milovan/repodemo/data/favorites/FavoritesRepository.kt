package com.milovan.repodemo.data.favorites

interface FavoritesRepository {
    suspend fun createFavorite(id: String, description: String)
    suspend fun getFavorites(): List<Favorite>
    suspend fun deleteFavorite(id: String)
}