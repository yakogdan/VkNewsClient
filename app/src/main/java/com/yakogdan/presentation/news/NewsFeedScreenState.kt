package com.yakogdan.presentation.news

import com.yakogdan.domain.model.FeedPost

sealed class NewsFeedScreenState {

    object Initial: NewsFeedScreenState()

    data class Posts(
        val posts: List<FeedPost>,
        val nextDataIsLoading: Boolean = false
    ) : NewsFeedScreenState()
}