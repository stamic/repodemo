package com.milovan.repodemo.di

import android.content.Context
import com.milovan.repodemo.data.repos.ReposRepository
import com.milovan.repodemo.data.repos.ReposRepositoryFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideItemsRepository(@ApplicationContext context: Context): ReposRepository {
        val repo = ReposRepositoryFactory.createOfflineRepository(context)
        return repo
    }
}