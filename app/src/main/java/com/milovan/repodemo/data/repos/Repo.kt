package com.milovan.repodemo.data.repos



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