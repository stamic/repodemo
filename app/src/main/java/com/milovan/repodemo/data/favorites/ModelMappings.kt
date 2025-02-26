package com.milovan.repodemo.data.favorites

fun Favorite.toEntity() = FavoriteEntity(
    id = id,
    description = description
)

fun FavoriteEntity.toExternal() = Favorite(
    id = id,
    description = description
)