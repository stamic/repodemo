package com.milovan.repodemo.ui.favoritecommon

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.milovan.repodemo.databinding.FragmentFavoritesBinding

fun FragmentFavoritesBinding.bindAdapter(favoritesAdapter: FavoritesAdapter) {
    list.adapter = favoritesAdapter
    list.layoutManager = LinearLayoutManager(list.context)
    val decoration = DividerItemDecoration(list.context, DividerItemDecoration.VERTICAL)
    list.addItemDecoration(decoration)
}