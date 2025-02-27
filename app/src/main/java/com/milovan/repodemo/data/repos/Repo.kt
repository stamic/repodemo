package com.milovan.repodemo.data.repos

import com.milovan.repodemo.data.repos.remote.OwnerNetwork
import com.milovan.repodemo.data.repos.remote.RepoNetwork


data class Repo (
    val id: Int = 0,
    val name: String = "",
    val owner: Owner = Owner(),
    val description: String = "",
    val stargazersCount: Int = 0,
    val watchersCount: Int = 0,
    val language: String = "",
    val forksCount: Int = 0,
    val openIssues: Int = 0,
)

data class Owner(
    val login: String = "",
    val avatarUrl: String = ""
)

fun OwnerNetwork.toExternal() = Owner(
    login = login ?: "",
    avatarUrl = avatar_url ?: ""
)

fun RepoNetwork.toExternal() = Repo(
    id = id,
    name = name ?: "",
    owner = owner?.toExternal() ?: Owner(),
    description = description ?: "",
    stargazersCount = stargazers_count,
    watchersCount = watchers_count,
    language = language ?: "",
    forksCount = forks_count,
    openIssues = open_issues
)