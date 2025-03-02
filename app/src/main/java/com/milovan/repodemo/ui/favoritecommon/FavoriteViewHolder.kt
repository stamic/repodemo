package com.milovan.repodemo.ui.favoritecommon

import androidx.recyclerview.widget.RecyclerView
import com.milovan.repodemo.databinding.ViewholderFavoriteBinding

class FavoriteViewHolder(
    private val binding: ViewholderFavoriteBinding,
    private val onRemoveClicked: (index: Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRemove.setOnClickListener {
            onRemoveClicked.invoke(bindingAdapterPosition)
        }
    }

    fun bind(item: FavoriteUi) {
        binding.apply {
            textName.text = item.name
        }
    }
}