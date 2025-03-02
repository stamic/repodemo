package com.milovan.repodemo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.milovan.repodemo.data.database.favoritecontribs.FavoriteContributor
import com.milovan.repodemo.data.database.favoritecontribs.FavoriteContributorDao
import com.milovan.repodemo.data.database.favoriterepos.FavoriteRepo
import com.milovan.repodemo.data.database.favoriterepos.FavoriteRepoDao
import com.milovan.repodemo.data.database.repos.RemoteKeys
import com.milovan.repodemo.data.database.repos.RemoteKeysDao
import com.milovan.repodemo.data.database.repos.Repo
import com.milovan.repodemo.data.database.repos.RepoDao

@Database(entities = [
    Repo::class,
    RemoteKeys::class,
    FavoriteContributor::class,
    FavoriteRepo::class], version = 2, exportSchema = false)
abstract class RepoDemoDatabase : RoomDatabase() {
    abstract fun reposDao(): RepoDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun favoriteContributorsDao(): FavoriteContributorDao
    abstract fun favoriteReposDao(): FavoriteRepoDao
}