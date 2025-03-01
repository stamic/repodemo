package com.milovan.repodemo.di

import android.content.Context
import com.milovan.repodemo.data.repos.ReposRepository
import com.milovan.repodemo.data.repos.ReposRepositoryFactory
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
    fun provideRepoWithMediator(@ApplicationContext context: Context): ReposRepository {
        val repo = ReposRepositoryFactory.createRepoWithMediator(context)
        return repo
    }


    @Singleton
    @Provides
    @RepoSimple
    fun provideRepoSimple(@ApplicationContext context: Context): ReposRepository {
        val repo = ReposRepositoryFactory.createRepoWithoutDatabase()
        return repo
    }

}