package com.milovan.repodemo.ui.favoritecommon

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        binding.textName.text = item.name
        Glide.with(binding.root.context)
            .load(item.avatarUrl)
            .into(binding.imgAvatar)
    }
}