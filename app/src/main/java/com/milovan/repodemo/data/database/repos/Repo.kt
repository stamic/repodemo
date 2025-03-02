package com.milovan.repodemo.data.database.repos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.milovan.repodemo.data.network.repos.RepoNetwork

@Entity(tableName = "repo")
data class Repo (
    @PrimaryKey val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val ownerLogin: String = "",
    val ownerAvatarUrl: String = "",
    val stars: Int = 0,
    val watchersCount: Int = 0,
    val language: String = "",
    val forksCount: Int = 0,
    val openIssues: Int = 0,
)

fun RepoNetwork.toExternal() = Repo(
    id = id,
    name = name ?: "",
    description = description ?: "",
    ownerLogin = owner?.login ?: "",
    ownerAvatarUrl = owner?.avatar_url ?: "",
    stars = stargazers_count,
    watchersCount = watchers_count,
    language = language ?: "",
    forksCount = forks_count,
    openIssues = open_issues
)