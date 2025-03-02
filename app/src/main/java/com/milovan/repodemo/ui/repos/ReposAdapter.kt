package com.milovan.repodemo.ui.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.milovan.repodemo.data.database.repos.Repo
import com.milovan.repodemo.databinding.ViewholderRepoBinding

class ReposAdapter(
    private val listener: Listener
) : PagingDataAdapter<RepoUi, RepoViewHolder>(DiffCallback) {

    interface Listener {
        fun onItemClicked(repo: Repo)
        fun onFavoriteClicked(repo: Repo)
    }

    private val holderListener = object : RepoViewHolder.Listener {
        override fun onItemClicked(index: Int) {
            val item = getItem(index)
            item?.let {
                listener.onItemClicked(it.repo)
            }

        }

        override fun onFavoriteClicked(index: Int) {
            val item = getItem(index)
            item?.let {
                listener.onFavoriteClicked(it.repo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder =
        RepoViewHolder(
            binding = ViewholderRepoBinding.inflate(
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
        private val DiffCallback = object : DiffUtil.ItemCallback<RepoUi>() {
            override fun areItemsTheSame(oldItem: RepoUi, newItem: RepoUi): Boolean =
                oldItem.repo.id == newItem.repo.id

            override fun areContentsTheSame(oldItem: RepoUi, newItem: RepoUi): Boolean =
                oldItem == newItem
        }
    }
}
