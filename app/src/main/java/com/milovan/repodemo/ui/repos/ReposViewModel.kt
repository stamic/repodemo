package com.milovan.repodemo.ui.repos

import androidx.lifecycle.ViewModel
import com.milovan.repodemo.data.repos.ReposRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ReposViewModel @Inject constructor(
    itemsRepository: ReposRepository
) : ViewModel() {
    val items = itemsRepository.getReposStream()
}