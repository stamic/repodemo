package com.milovan.repodemo.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContributorNetwork(
    @SerialName("login") var login: String? = null,
    @SerialName("avatar_url") var avatarUrl: String? = null
)
