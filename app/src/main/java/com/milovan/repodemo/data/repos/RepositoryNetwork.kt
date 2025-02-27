package com.milovan.repodemo.data.repos
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryNetwork (
    val id: Int = 0,
    val name: String? = null,
    val owner: Owner? = null,
    val description: String? = null,
    val stargazers_count: Int = 0,
    val watchers_count: Int = 0,
    val language: String? = null,
    val forks_count: Int = 0,
    val open_issues: Int = 0,
)

@Serializable
data class Owner(
    val login: String? = null,
    val avatar_url: String? = null
)
