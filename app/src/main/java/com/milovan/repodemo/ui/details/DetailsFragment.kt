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
import com.bumptech.glide.Glide
import com.milovan.repodemo.R
import com.milovan.repodemo.data.details.RepoDetails
import com.milovan.repodemo.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// ne uspevam da uradim ovako
//@Parcelize
//class DetailsFragmentArgs(val owner: String, val name: String): Parcelable

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    // ne radi nesto
//    private val args: DetailsFragmentArgs by navArgs()

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var contributorsAdapter: ContributorsAdapter
    private val itemFavoriteClickHandler = { id: Long ->
        viewModel.toggleFavoriteContributor(id)
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

        val argString = arguments?.getString("repoId") ?: "square:okhttp"
        val args = argString.split(':')
        if (args.size == 2) {
            viewModel.setRepository(args[0], args[1])
        }


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
                updateDetails(state.repoDetails)
            }
        }
    }

    private fun updateDetails(repoDetails: RepoDetails) {
        binding.apply {
            with(repoDetails) {
                textRepoName.text = name
                textDescription.text = description
                textOwnerLogin.text = "Owner: ${ownerLogin}"
                textLanguage.text = language
                textStars.text = "${stars} stars"
                textForks.text = "Forks: ${forksCount}"
                textIssues.text = "Issues: ${openIssues}"
                textWatchers.text = "Watchers: $watchersCount"

                Glide.with(binding.root.context)
                    .load(ownerAvatarUrl)
                    .into(binding.imgAvatar)
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