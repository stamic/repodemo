package com.milovan.repodemo.ui.repos

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.milovan.repodemo.R
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
            with(item.repo) {
                textRepoName.text = name
                textDescription.text = description
                textOwnerLogin.text = "Owner: ${ownerLogin}"
                textLanguage.text = language
                textStars.text = "${stars} stars"
                textForks.text = "Forks: ${forksCount}"
                textIssues.text = "Issues: ${openIssues}"
                textWatchers.text = "Watchers: $watchersCount"
            }

            val imgResource = if (item.isFavorite) {
                R.drawable.ic_favorite_24dp_filled
            } else {
                R.drawable.ic_favorite_24dp_outlined
            }
            btnFavorite.setImageResource(imgResource)

            Glide.with(binding.root.context)
                .load(item.repo.ownerAvatarUrl)
                .into(binding.imgAvatar)
        }
    }
}