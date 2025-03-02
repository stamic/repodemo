package com.milovan.repodemo.ui.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.milovan.repodemo.data.database.repos.Repo
import com.milovan.repodemo.data.favoriterepos.FavoriteReposRepository
import com.milovan.repodemo.data.repos.ReposRepository
import com.milovan.repodemo.di.RepoSimple
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class Repos2ViewModel @Inject constructor(
    @RepoSimple private val itemsRepository: ReposRepository,
    private val favoriteReposRepository: FavoriteReposRepository,
) : ViewModel() {

    private val _favoriteIdsStream: Flow<HashSet<Long>> =
        favoriteReposRepository.getStream().map { repos -> repos.map { it.id }.toHashSet()}

    private val _pagedReposStream = itemsRepository.getReposStream().cachedIn(viewModelScope)

    fun toggleFavoriteRepo(repo: Repo) {
        viewModelScope.launch {
            val favoriteRepo = favoriteReposRepository.getById(repo.id)
            if (favoriteRepo == null) {
                favoriteReposRepository.create(repo.id, repo.name, repo.ownerAvatarUrl)
            } else {
                favoriteReposRepository.deleteById(repo.id)
            }
        }
    }

    val pagedReposUiStream = _pagedReposStream.combine(_favoriteIdsStream) { pagingData, favoriteIds ->
        makeFavoritePagedRepos(pagingData, favoriteIds)}

    private fun makeFavoritePagedRepos(pagingData: PagingData<Repo>, favoriteIds: HashSet<Long>): PagingData<RepoUi> {
        return pagingData.map { repo ->
            val isFavorite = favoriteIds.contains(repo.id)
            if (isFavorite) {
                Timber.d("${repo.name} je favorite")
            }
            RepoUi(repo, isFavorite)
        }
    }

}