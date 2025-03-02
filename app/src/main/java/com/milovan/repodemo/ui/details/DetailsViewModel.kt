package com.milovan.repodemo.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milovan.repodemo.data.details.Contributor
import com.milovan.repodemo.data.details.RepoDetails
import com.milovan.repodemo.data.details.RepoDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
    private val repository: RepoDetailsRepository
) : ViewModel() {
    private val _detailsUiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val detailsUiState = _detailsUiState.asStateFlow()

    private val _contributorsUiState = MutableStateFlow<ContributorsUistate>(
        ContributorsUistate.Loading
    )
    val contributorsUiState = _contributorsUiState.asStateFlow()


    private val favorites = hashSetOf<String>("udalov", "mglukhikh")

    fun setRepository(owner: String, name: String) {
        fetchDetailsAndContributors(owner, name)
    }

    private fun fetchDetailsAndContributors(owner: String, name: String) {
        viewModelScope.launch {
            val details = fetchDetails(owner, name)
            _detailsUiState.update { details }
            if (details is DetailsUiState.Success) {
                val contributors = fetchContributors(details.repoDetails.contributorsUrl)
                _contributorsUiState.update { contributors }
            }
        }
    }

    private suspend fun fetchDetails(owner: String, name: String): DetailsUiState {
       val result = try {
            val details = repository.getRepoDetails(owner, name)
           DetailsUiState.Success(details)
        } catch (e: IOException) {
           DetailsUiState.Error(e)
        } catch (e: HttpException) {
           DetailsUiState.Error(e)
        }

        return result
    }

    private suspend fun fetchContributors(url: String): ContributorsUistate {
        val result = try {
            val contributors = repository.getContributors(url)
            val contributorsFavorite = addFavoriteFlag(contributors)
            ContributorsUistate.Success(contributorsFavorite)
        } catch (e: IOException) {
            ContributorsUistate.Error(e)
        } catch (e: HttpException) {
            ContributorsUistate.Error(e)
        }

        return result
    }

    private fun addFavoriteFlag(contributors: List<Contributor>): List<ContributorUi> {
        val result = mutableListOf<ContributorUi>()
        for (contrib in contributors) {
            val isFavorite = favorites.contains(contrib.login)
            val contribUi = ContributorUi(contrib, isFavorite)
            result.add(contribUi)
        }
        return result
    }
}