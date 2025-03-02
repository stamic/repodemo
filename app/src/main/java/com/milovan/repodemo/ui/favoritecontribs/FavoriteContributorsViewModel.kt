package com.milovan.repodemo.ui.favoritecontribs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milovan.repodemo.data.database.favoritecontribs.FavoriteContributor
import com.milovan.repodemo.data.favoritecontribs.FavoriteContributorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

private fun FavoriteContributor.toUi() = FavoriteUi(
    id = id,
    name = login,
    avatarUrl = avatarUrl
)

@HiltViewModel
class FavoriteContributorsViewModel @Inject constructor(
    private val favoriteRepository: FavoriteContributorsRepository
) : ViewModel() {
    private val _favoriteUiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    val favoriteUiState = _favoriteUiState.asStateFlow()

    init {
        initialUpdate()
    }

    fun deleteItem(id: Long) {
        viewModelScope.launch {
            favoriteRepository.deleteById(id)
            fetchAndUpdate()
        }
    }

    private fun initialUpdate() {
        viewModelScope.launch {
            fetchAndUpdate()
        }
    }

    private suspend fun fetchAndUpdate() {
        try {
            val favorites = favoriteRepository.getAll()
            val favoritesUi = favorites.map { it.toUi() }
            _favoriteUiState.value = FavoriteUiState.Success(favoritesUi)
        } catch (e: IOException) {
            _favoriteUiState.value = FavoriteUiState.Error(e)
        } catch (e: HttpException) {
            _favoriteUiState.value = FavoriteUiState.Error(e)
        }

    }
}