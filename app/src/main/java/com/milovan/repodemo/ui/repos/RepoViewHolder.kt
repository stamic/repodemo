package com.milovan.repodemo.ui.repos

import androidx.recyclerview.widget.RecyclerView
import com.milovan.repodemo.databinding.ViewholderRepoBinding

class RepoViewHolder(
    private val binding: ViewholderRepoBinding,
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

    fun bind(item: RepoUi) {
        binding.apply {
            textRepoId.text = item.repo.id.toString()
            textRepoName.text = item.repo.name
        }
    }
}