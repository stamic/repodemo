package com.milovan.repodemo.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.milovan.repodemo.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var contributorsAdapter: ContributorsAdapter
    private val itemFavoriteClickHandler = { name: String ->
        Timber.d("clicked favorite $name")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contributorsAdapter = ContributorsAdapter(itemFavoriteClickHandler)
        binding.bindAdapter(contributorsAdapter)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailsUiState.collect {
                    handleUiState(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.contributorsUiState.collect {
                    handleContributors(it)
                }
            }
        }
    }

    private fun handleContributors(state: ContributorsUistate) {
        when (state) {
            is ContributorsUistate.Error -> {}
            ContributorsUistate.Loading -> {}
            is ContributorsUistate.Success -> {
                contributorsAdapter.submitList(state.contributors)
            }
        }
    }

    private fun handleUiState(state: DetailsUiState) {
        when (state) {
            is DetailsUiState.Error -> {

            }
            DetailsUiState.Loading -> {

            }
            is DetailsUiState.Success -> {

            }
        }
    }
}

private fun FragmentDetailsBinding.bindAdapter(contributorsAdapter: ContributorsAdapter) {
    list.adapter = contributorsAdapter
    list.layoutManager = LinearLayoutManager(list.context)
    val decoration = DividerItemDecoration(list.context, DividerItemDecoration.VERTICAL)
    list.addItemDecoration(decoration)
}