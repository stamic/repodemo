package com.milovan.repodemo.ui.repos

import androidx.recyclerview.widget.RecyclerView
import com.milovan.repodemo.databinding.RepoViewholderBinding

class RepoViewHolder(
    private val binding: RepoViewholderBinding,
    private val listener: Listener
) : RecyclerView.ViewHolder(binding.root) {

    interface Listener {
        fun onItemClicked(index: Int)
        fun onFavoriteClicked(index: Int)
    }

    init {
        binding.root.setOnClickListener{
            listener.onItemClicked(bindingAdapterPosition)
        }

        binding.btnFavorite.setOnClickListener {
            listener.onFavoriteClicked(bindingAdapterPosition)
        }
    }

    fun bind(item: RepoAndFlag) {
        binding.apply {
            binding.textRepoId.text = item.repo.id.toString()
            binding.textRepoName.text = item.repo.name
        }
    }
}