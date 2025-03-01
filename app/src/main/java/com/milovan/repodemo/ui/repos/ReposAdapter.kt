package com.milovan.repodemo.ui.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.milovan.repodemo.databinding.RepoViewholderBinding
import timber.log.Timber

class ReposAdapter(
    private val listener: Listener
) : PagingDataAdapter<RepoAndFlag, RepoViewHolder>(REPO_DIFF_CALLBACK) {

    interface Listener {
        fun onItemClicked(itemId: Long)
        fun onFavoriteClicked(itemId: Long)
    }

    private val holderListener = object : RepoViewHolder.Listener {
        override fun onItemClicked(index: Int) {
            val item = getItem(index)
            item?.let {
                listener.onItemClicked(it.repo.id)
            }

        }

        override fun onFavoriteClicked(index: Int) {
            val item = getItem(index)
            item?.let {
                listener.onFavoriteClicked(it.repo.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder =
        RepoViewHolder(
            binding = RepoViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            listener = holderListener
        )

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val REPO_DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepoAndFlag>() {
            override fun areItemsTheSame(oldItem: RepoAndFlag, newItem: RepoAndFlag): Boolean =
                oldItem.repo.id == newItem.repo.id

            override fun areContentsTheSame(oldItem: RepoAndFlag, newItem: RepoAndFlag): Boolean =
                oldItem == newItem
        }
    }
}
