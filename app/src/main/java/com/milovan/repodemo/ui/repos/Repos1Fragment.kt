package com.milovan.repodemo.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.milovan.repodemo.data.database.repos.Repo
import com.milovan.repodemo.databinding.FragmentReposBinding
import com.milovan.repodemo.ui.details.DetailsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Repos1Fragment : Fragment() {
    private var _binding: FragmentReposBinding? = null
    private val binding get() = _binding!!

    private val viewModel: Repos1ViewModel by viewModels()
    lateinit var reposAdapter: ReposAdapter

    val adapterListener = object : ReposAdapter.Listener {
        override fun onItemClicked(repo: Repo) {
            val action = DetailsFragmentDirections.actionToDetailsFragment(
                "${repo.ownerLogin}:${repo.name}"
            )
            findNavController().navigate(action)
        }

        override fun onFavoriteClicked(repo: Repo) {
            viewModel.toggleFavoriteRepo(repo)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reposAdapter = ReposAdapter(adapterListener)
        binding.bindAdapter(reposAdapter)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pagedReposUiStream.collectLatest {
                    reposAdapter.submitData(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                reposAdapter.loadStateFlow.collect {
                    binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                }
            }
        }
    }
}