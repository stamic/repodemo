package com.milovan.repodemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.milovan.repodemo.data.repos.remote.RepositoriesRetrofitApi
import com.milovan.repodemo.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.reposFragment)
//            testCall()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun testCall() {

        val jsonNetwork = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }

        val networkApi = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(
                jsonNetwork.asConverterFactory("application/json".toMediaType()
                ))
            .build()
            .create(RepositoriesRetrofitApi::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
//            val result = networkApi.getRepositoriesTest()
            val result = networkApi.getRepositories(20, 1)
            Timber.d("${result.message()}")
        }
    }


}