package com.milovan.repodemo.data.api
import kotlinx.serialization.Serializable

@Serializable
data class RepoDetailsNetwork(
    val id: Long = 0,
    val name: String? = null,
    val owner: OwnerDetailsNetwork? = null,
    val description: String? = null,
    val stargazers_count: Int = 0,
    val watchers_count: Int = 0,
    val language: String? = null,
    val forks_count: Int = 0,
    val open_issues: Int = 0,

    var contributors_url: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
    var default_branch: String? = null
)

@Serializable
data class OwnerDetailsNetwork(
    val login: String? = null,
    val avatar_url: String? = null
)




