package com.milovan.repodemo.data.network.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContributorNetwork(
    @SerialName("login") var login: String? = null,
    @SerialName("avatar_url") var avatarUrl: String? = null
)
