package com.milovan.repodemo.di

import android.content.Context
import com.milovan.repodemo.data.details.DetailsFactory
import com.milovan.repodemo.data.details.RepoDetailsRepository
import com.milovan.repodemo.data.repos.ReposRepository
import com.milovan.repodemo.data.repos.ReposFactory
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
object RepositoryModule {

    @Singleton
    @Provides
    @RepoWithMediator
    fun provideReposRepositoryWithMediator(@ApplicationContext context: Context): ReposRepository {
        val repo = ReposFactory.createWithMediator(context)
        return repo
    }


    @Singleton
    @Provides
    @RepoSimple
    fun provideReposRepositorySimple(): ReposRepository {
        val repo = ReposFactory.createWithoutDatabase()
        return repo
    }

    @Singleton
    @Provides
    fun provideReposDetailsRepository(): RepoDetailsRepository {
        val repo = DetailsFactory.create()
        return repo
    }

}