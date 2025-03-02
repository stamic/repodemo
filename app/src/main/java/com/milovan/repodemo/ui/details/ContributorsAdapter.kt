package com.milovan.repodemo.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.milovan.repodemo.databinding.ViewholderContributorBinding

class ContributorsAdapter(
    val onFavoriteClicked: (loginName: String) -> Unit
) : ListAdapter<ContributorUi, ContributorViewHolder>(DiffCallback) {

    private val itemFavoriteClickHandler = { ndx: Int ->
        val item = getItem(ndx)
        if (item != null) {
            onFavoriteClicked(item.contributor.login)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val binding = ViewholderContributorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val vh = ContributorViewHolder(
            binding,
            itemFavoriteClickHandler
        )
        return vh
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ContributorUi>() {
            override fun areItemsTheSame(oldItem: ContributorUi, newItem: ContributorUi): Boolean {
                return oldItem.contributor.login == newItem.contributor.login
            }

            override fun areContentsTheSame(
                oldItem: ContributorUi,
                newItem: ContributorUi
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}