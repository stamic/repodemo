package com.milovan.repodemo.data.favorites

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object FavoriteRepositoryFactory {
    fun createRepository(context: Context): FavoritesRepository {
        val database = Room.databaseBuilder(
            context.applicationContext,
            FavoriteDatabase::class.java,
            "favorites.dat"
        ).build()

        val repository = FavoritesRepositoryDefault(
            database.favoriteDao(),
            Dispatchers.Default,
            CoroutineScope(SupervisorJob() + Dispatchers.Default)
        )
        return repository
    }
}