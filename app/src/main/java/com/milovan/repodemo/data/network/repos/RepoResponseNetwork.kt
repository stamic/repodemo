package com.milovan.repodemo.data.network.repos
import kotlinx.serialization.Serializable

@Serializable
data class RepoResponseNetwork(
    val total_count: Int,
    val items: List<RepoNetwork> = listOf()
)
