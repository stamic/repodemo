package com.milovan.repodemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.milovan.repodemo.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnExample1.setOnClickListener {
            findNavController().navigate(R.id.repos1Fragment)
        }

        binding.btnExample2.setOnClickListener {
            findNavController().navigate(R.id.repos2Fragment)
        }

        binding.btnFavoriteContribs.setOnClickListener {
            findNavController().navigate(R.id.favoriteContributorsFragment)
        }

        binding.btnFavoriteRepos.setOnClickListener {
            findNavController().navigate(R.id.favoriteReposFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}