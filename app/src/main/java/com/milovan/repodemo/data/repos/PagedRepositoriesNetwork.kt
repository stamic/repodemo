package com.milovan.repodemo.data.repos
import kotlinx.serialization.Serializable

@Serializable
data class PagedRepositoriesNetwork(
    val total_count: Int,
    val items: List<RepositoryNetwork>?
)
