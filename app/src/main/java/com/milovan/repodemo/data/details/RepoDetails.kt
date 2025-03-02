package com.milovan.repodemo.data.details

import com.milovan.repodemo.data.network.details.ContributorNetwork
import com.milovan.repodemo.data.network.details.RepoDetailsNetwork

data class RepoDetails(
    val id: Long,
    val name: String,
    val description: String,
    val ownerLogin: String,
    val ownerAvatarUrl: String ,
    val stars: Int,
    val watchersCount: Int,
    val language: String,
    val forksCount: Int,
    val openIssues: Int,
    var contributorsUrl: String,
    val createdAt: String,
    val updatedAt: String,
    val defaultBranch: String
)

data class Contributor(
    val id: Long,
    val login: String,
    val avatarUrl: String
)

data class RepoDetailsAndContributors(
    val details: RepoDetails,
    val contributors: List<Contributor>
)

fun ContributorNetwork.toExternal() = Contributor(
    id = id ?: 0,
    login = login ?: "",
    avatarUrl = avatarUrl ?: ""
)

fun RepoDetailsNetwork.toExternal() = RepoDetails(
    id = id,
    name = name ?: "",
    description = description ?: "",
    ownerLogin = owner?.login ?: "",
    ownerAvatarUrl = owner?.avatar_url ?: "",
    stars = stargazers_count,
    watchersCount = watchers_count,
    language = language ?: "",
    forksCount = forks_count,
    openIssues = open_issues,
    contributorsUrl = contributors_url ?: "",
    createdAt = created_at ?: "",
    updatedAt = updated_at ?: "",
    defaultBranch = default_branch ?: ""
)
