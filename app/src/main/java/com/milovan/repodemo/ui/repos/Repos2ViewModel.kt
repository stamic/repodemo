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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Repos2ViewModel @Inject constructor(
    @RepoSimple private val itemsRepository: ReposRepository,
    private val favoriteReposRepository: FavoriteReposRepository
) : ViewModel() {

    private val _favoriteIdsStream: Flow<HashSet<Long>> =
        favoriteReposRepository.getStream().map { repos -> repos.map { it.id }.toHashSet()}

    private val _reposStream = itemsRepository.getReposStream().cachedIn(viewModelScope)

    private val _searchQuery = MutableStateFlow("")
    private val _searchResultStream = _searchQuery.flatMapLatest { query ->
        itemsRepository.getReposSearchStream(query)
    }.cachedIn(viewModelScope)

    private val _searchResultSnapshot = MutableStateFlow<List<Repo>>(listOf())

    // moze i ovo da se koristi
    val reposUiStream1 = combine(_reposStream, _searchResultSnapshot, _favoriteIdsStream, _searchQuery) {
        allPagingData, repoList, favoriteIds, queryString ->
        if(queryString.isEmpty()) {
            makeFavoritePagedRepos(allPagingData, favoriteIds)
        } else {
            val pagingData = PagingData.from(repoList)
            makeFavoritePagedRepos(pagingData, favoriteIds)
        }
    }

    val reposUiStream2 = combine(_reposStream, _searchResultStream, _favoriteIdsStream, _searchQuery) {
            allPagingData, searchPagingData, favoriteIds, queryString ->
        if(queryString.isEmpty()) {
            makeFavoritePagedRepos(allPagingData, favoriteIds)
        } else {
            makeFavoritePagedRepos(searchPagingData, favoriteIds)
        }
    }

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

    private fun makeFavoritePagedRepos(
        pagingData: PagingData<Repo>, favoriteIds: HashSet<Long>
    ): PagingData<RepoUi> {
        return pagingData.map { repo ->
            val isFavorite = favoriteIds.contains(repo.id)
            RepoUi(repo, isFavorite)
        }
    }

    fun searchRepos(query: String) {
        _searchQuery.update { query }
        viewModelScope.launch {
            val repos = itemsRepository.searchByQuery(query)
            _searchResultSnapshot.update { repos }
        }
    }

}