package com.milovan.repodemo.ui.repos

import com.milovan.repodemo.data.repos.local.Repo

data class RepoUi(
    val repo: Repo,
    val isFavorite: Boolean
)
