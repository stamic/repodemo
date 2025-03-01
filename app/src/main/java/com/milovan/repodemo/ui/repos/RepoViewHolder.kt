package com.milovan.repodemo.ui.repos

import androidx.recyclerview.widget.RecyclerView
import com.milovan.repodemo.databinding.RepoViewholderBinding

class RepoViewHolder(
    private val binding: RepoViewholderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RepoAndFlag) {
        binding.apply {
            binding.repoId.text = item.repo.id.toString()
            binding.repoName.text = item.repo.name
        }
    }
}