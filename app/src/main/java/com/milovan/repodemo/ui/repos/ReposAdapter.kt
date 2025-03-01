package com.milovan.repodemo.ui.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.milovan.repodemo.data.repos.local.Repo
import com.milovan.repodemo.databinding.RepoViewholderBinding

class ReposAdapter : PagingDataAdapter<RepoAndFlag, RepoViewHolder>(REPO_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder =
        RepoViewHolder(
            RepoViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
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
