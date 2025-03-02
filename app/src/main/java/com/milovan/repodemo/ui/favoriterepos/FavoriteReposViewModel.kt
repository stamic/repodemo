package com.milovan.repodemo.ui.favoriterepos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milovan.repodemo.data.database.favoriterepos.FavoriteRepo
import com.milovan.repodemo.data.favoriterepos.FavoriteReposRepository
import com.milovan.repodemo.ui.favoritecommon.FavoriteUi
import com.milovan.repodemo.ui.favoritecommon.FavoriteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

private fun FavoriteRepo.toUi() = FavoriteUi(
    id = id,
    name = name,
    avatarUrl = avatarUrl
)

@HiltViewModel
class FavoriteReposViewModel @Inject constructor(
    private val favoriteRepository: FavoriteReposRepository
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