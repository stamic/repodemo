package com.milovan.repodemo.ui.favoriterepos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.milovan.repodemo.databinding.FragmentFavoritesBinding
import com.milovan.repodemo.ui.favoritecommon.FavoriteUiState
import com.milovan.repodemo.ui.favoritecommon.FavoritesAdapter
import com.milovan.repodemo.ui.favoritecommon.bindAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteReposFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteReposViewModel by viewModels()
    private lateinit var favoritesAdapter: FavoritesAdapter
    private val itemRemoveClickHandler = { id: Long ->
        viewModel.deleteItem(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesAdapter = FavoritesAdapter(itemRemoveClickHandler)
        binding.bindAdapter(favoritesAdapter)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoriteUiState.collect {
                    handleFavorites(it)
                }
            }
        }
    }

    private fun handleFavorites(state: FavoriteUiState) {
        when (state) {
            is FavoriteUiState.Error -> {}
            FavoriteUiState.Loading -> {}
            is FavoriteUiState.Success -> {
                favoritesAdapter.submitList(state.favorites)
            }
        }

    }
}

