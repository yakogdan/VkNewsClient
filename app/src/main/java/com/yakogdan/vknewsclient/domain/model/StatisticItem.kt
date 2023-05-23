package com.yakogdan.vknewsclient.domain.model

data class StatisticItem (
    val type: StatisticType,
    val count: Int = 0
)

enum class StatisticType {
    VIEWS, COMMENTS, SHARES, LIKES
}