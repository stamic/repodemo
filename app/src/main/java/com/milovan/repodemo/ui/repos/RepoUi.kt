package com.milovan.repodemo.ui.repos

import com.milovan.repodemo.data.database.repos.Repo


data class RepoUi(
    val repo: Repo,
    val isFavorite: Boolean
)
