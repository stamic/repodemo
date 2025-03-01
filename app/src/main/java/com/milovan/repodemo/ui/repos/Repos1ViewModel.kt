package com.milovan.repodemo.ui.repos

import androidx.lifecycle.ViewModel
import androidx.paging.filter
import androidx.paging.map
import com.milovan.repodemo.data.repos.ReposRepository
import com.milovan.repodemo.di.RepoSimple
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class Repos1ViewModel @Inject constructor(
    @RepoSimple itemsRepository: ReposRepository
) : ViewModel() {
    private val items = itemsRepository.getReposStream()
    val items1 = itemsRepository.getReposStream().map { pagingData ->
        pagingData.map { repo -> RepoAndFlag(repo, false) }
    }

    val items2 = items1.map { p -> p.filter { r -> r.repo.name.contains("ko") } }

}