package com.yakogdan.presentation.news

import com.yakogdan.vknewsclient.domain.model.FeedPost

sealed class NewsFeedScreenState {

    object Initial: NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()
}
