package com.milovan.repodemo.di

import android.content.Context
import androidx.room.Room
import com.milovan.repodemo.data.database.RepoDemoDatabase
import com.milovan.repodemo.data.database.favoritecontribs.FavoriteContributorDao
import com.milovan.repodemo.data.details.RepoDetailsRepository
import com.milovan.repodemo.data.details.RepoDetailsRepositoryDefault
import com.milovan.repodemo.data.favorites.FavoriteContributorsRepository
import com.milovan.repodemo.data.favorites.FavoriteContributorsRepositoryDefault
import com.milovan.repodemo.data.network.NetworkDataSource
import com.milovan.repodemo.data.network.NetworkDataSourceRetrofit
import com.milovan.repodemo.data.repos.ReposRepository
import com.milovan.repodemo.data.repos.ReposRepositorySimpleOnline
import com.milovan.repodemo.data.repos.ReposRepositoryWithMediator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RepoSimple

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RepoWithMediator


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    @RepoWithMediator
    abstract fun bindReposMediatorRepository(
        repository: ReposRepositoryWithMediator
    ): ReposRepository

    @Singleton
    @Binds
    @RepoSimple
    abstract fun bindReposSimpleRepository(
        repository: ReposRepositorySimpleOnline
    ): ReposRepository

    @Singleton
    @Binds
    abstract fun bindDetailsRepository(
        repository: RepoDetailsRepositoryDefault
    ): RepoDetailsRepository

    @Singleton
    @Binds
    abstract fun bindFavoriteContributorRepository(
        repository: FavoriteContributorsRepositoryDefault
    ): FavoriteContributorsRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    @Binds
    abstract fun bindNetworkDataSource(
        networkDataSource: NetworkDataSourceRetrofit
    ): NetworkDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): RepoDemoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RepoDemoDatabase::class.java,
            "database.dat"
        ).build()
    }

    @Provides
    fun provideFavoriteContributorsDao(
        database: RepoDemoDatabase
    ): FavoriteContributorDao = database.favoriteContributorsDao()

}

