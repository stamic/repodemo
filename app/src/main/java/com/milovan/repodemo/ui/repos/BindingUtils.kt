package com.milovan.repodemo.ui.repos

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.milovan.repodemo.databinding.FragmentReposBinding

fun FragmentReposBinding.bindAdapter(reposAdapter: ReposAdapter) {
    list.adapter = reposAdapter
    list.layoutManager = LinearLayoutManager(list.context)
    val decoration = DividerItemDecoration(list.context, DividerItemDecoration.VERTICAL)
    list.addItemDecoration(decoration)
}