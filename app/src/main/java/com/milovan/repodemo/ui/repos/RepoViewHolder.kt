package com.milovan.repodemo.ui.repos

import androidx.recyclerview.widget.RecyclerView
import com.milovan.repodemo.data.repos.Repo
import com.milovan.repodemo.databinding.RepoViewholderBinding

class RepoViewHolder(
    private val binding: RepoViewholderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repo: Repo) {
        binding.apply {
            binding.repoId.text = repo.id.toString()
            binding.repoName.text = repo.name
        }
    }
}