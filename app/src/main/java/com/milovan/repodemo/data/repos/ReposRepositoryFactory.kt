package com.milovan.repodemo.data.repos

object ReposRepositoryFactory {
    fun createRepository(): ReposRepository {
        return ReposRepositoryDefault()
    }
}