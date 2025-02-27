package com.milovan.repodemo.data.repos.remote
import kotlinx.serialization.Serializable

@Serializable
data class RepoResponsNetwork(
    val total_count: Int,
    val items: List<RepoNetwork>?
)
