package com.milovan.repodemo.ui.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.milovan.repodemo.data.repos.Repo
import com.milovan.repodemo.data.repos.ReposRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val ITEMS_PER_PAGE = 50

@HiltViewModel
class ReposViewModel @Inject constructor(
    itemsRepository: ReposRepository
) : ViewModel() {

    val items: Flow<PagingData<Repo>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { itemsRepository.reposPagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)
}