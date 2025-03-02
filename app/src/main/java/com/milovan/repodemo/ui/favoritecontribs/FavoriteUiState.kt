package com.milovan.repodemo.ui.favoritecontribs

sealed interface FavoriteUiState {
    data object Loading : FavoriteUiState
    data class Success(val favorites: List<FavoriteUi>) : FavoriteUiState
    data class Error(val throwable: Throwable) : FavoriteUiState
}