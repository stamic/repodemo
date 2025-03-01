package com.milovan.repodemo.data.repos.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.milovan.repodemo.data.api.OwnerNetwork
import com.milovan.repodemo.data.api.RepoNetwork

@Entity(tableName = "repo")
data class Repo (
    @PrimaryKey val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val stars: Int = 0,
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
    description = description ?: "",
    stars = stargazers_count,
    watchersCount = watchers_count,
    language = language ?: "",
    forksCount = forks_count,
    openIssues = open_issues
)