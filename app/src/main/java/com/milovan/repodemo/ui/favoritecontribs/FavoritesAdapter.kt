package com.milovan.repodemo.ui.favoritecontribs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.milovan.repodemo.databinding.ViewholderFavoriteBinding

class FavoritesAdapter(
    val onItemRemoveClicked: (id: Long) -> Unit
) : ListAdapter<FavoriteUi, FavoriteViewHolder>(DiffCallback) {

    private val removeClickHandler = { ndx: Int ->
        val item = getItem(ndx)
        if (item != null) {
            onItemRemoveClicked(item.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ViewholderFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val vh = FavoriteViewHolder(
            binding,
            removeClickHandler
        )
        return vh
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<FavoriteUi>() {
            override fun areItemsTheSame(oldItem: FavoriteUi, newItem: FavoriteUi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteUi,
                newItem: FavoriteUi
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}