package com.milovan.repodemo.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milovan.repodemo.data.details.Contributor
import com.milovan.repodemo.data.details.RepoDetails
import com.milovan.repodemo.data.details.RepoDetailsRepository
import com.milovan.repodemo.data.favoritecontribs.FavoriteContributorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

sealed interface DetailsUiState {
    data object Loading : DetailsUiState
    data class Success(val repoDetails: RepoDetails) : DetailsUiState
    data class Error(val throwable: Throwable) : DetailsUiState
}

sealed interface ContributorsUistate {
    data object Loading : ContributorsUistate
    data class Success(val contributors: List<ContributorUi>) : ContributorsUistate
    data class Error(val throwable: Throwable) : ContributorsUistate
}

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: RepoDetailsRepository,
    private val favoritesRepository: FavoriteContributorsRepository
) : ViewModel() {
    private val _detailsUiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val detailsUiState = _detailsUiState.asStateFlow()

    private val _contributorsUiState = MutableStateFlow<ContributorsUistate>(
        ContributorsUistate.Loading
    )
    val contributorsUiState = _contributorsUiState.asStateFlow()

    private val _contributors = mutableListOf<Contributor>()


    fun setRepository(owner: String, name: String) {
        fetchDetailsAndContributors(owner, name)
    }

    private fun fetchDetailsAndContributors(owner: String, name: String) {
        viewModelScope.launch {
            fetchDetails(owner, name)
            val detailsSuccess = _detailsUiState.value
            if (detailsSuccess is DetailsUiState.Success) {
                fetchContributors(detailsSuccess.repoDetails.contributorsUrl)
            }
        }
    }

    private suspend fun fetchDetails(owner: String, name: String) {
       try {
            val details = detailsRepository.getRepoDetails(owner, name)
           _detailsUiState.value = DetailsUiState.Success(details)
        } catch (e: IOException) {
           _detailsUiState.value = DetailsUiState.Error(e)
        } catch (e: HttpException) {
           _detailsUiState.value = DetailsUiState.Error(e)
        }

    }

    private suspend fun fetchContributors(url: String) {
        _contributors.clear()
        try {
            _contributors.addAll(detailsRepository.getContributors(url))
            updateContributorsUiState()
        } catch (e: IOException) {
            _contributorsUiState.value = ContributorsUistate.Error(e)
        } catch (e: HttpException) {
            _contributorsUiState.value = ContributorsUistate.Error(e)
        }

    }

    private suspend fun updateContributorsUiState() {
        val favoritesList = favoritesRepository.getAll()
        val favoriteSet = favoritesList.map { it.login }.toHashSet()

        val result = mutableListOf<ContributorUi>()
        for (contrib in _contributors) {
            val isFavorite = favoriteSet.contains(contrib.login)
            val contribUi = ContributorUi(contrib, isFavorite)
            result.add(contribUi)
        }

        _contributorsUiState.value = ContributorsUistate.Success(result)
    }

    fun toggleFavoriteContributor(id: Long) {
        viewModelScope.launch {
            val favoriteContributor = favoritesRepository.getById(id)
            if (favoriteContributor == null) {
                saveFavoriteContributor(id)
            } else {
                favoritesRepository.deleteById(id)
            }
            updateContributorsUiState()
        }
    }

    private suspend fun saveFavoriteContributor(id: Long) {
        val successState = _contributorsUiState.value
        if (successState is ContributorsUistate.Success) {
            val contribUi = successState.contributors.find { it.contributor.id == id }
            if (contribUi != null) {
                favoritesRepository.create(
                    contribUi.contributor.id,
                    contribUi.contributor.login,
                    contribUi.contributor.avatarUrl
                )
            }
        }
    }


}