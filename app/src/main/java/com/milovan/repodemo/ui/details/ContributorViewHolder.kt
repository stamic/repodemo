package com.milovan.repodemo.ui.details

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.milovan.repodemo.R
import com.milovan.repodemo.databinding.ViewholderContributorBinding

class ContributorViewHolder(
    private val binding: ViewholderContributorBinding,
    private val onFavClicked: (index: Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnFavorite.setOnClickListener {
            onFavClicked.invoke(bindingAdapterPosition)
        }
    }

    fun bind(item: ContributorUi) {
        binding.apply {
            textName.text = item.contributor.login
            Glide.with(binding.root.context)
                .load(item.contributor.avatarUrl)
                .into(binding.imgAvatar)

            val imgResource = if (item.isFavorite) {
                R.drawable.ic_favorite_24dp_filled
            } else {
                R.drawable.ic_favorite_24dp_outlined
            }
            btnFavorite.setImageResource(imgResource)
        }
    }
}