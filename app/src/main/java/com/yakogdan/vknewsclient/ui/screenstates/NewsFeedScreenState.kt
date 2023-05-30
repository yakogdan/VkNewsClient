package com.yakogdan.vknewsclient.ui.screenstates

import com.yakogdan.vknewsclient.domain.model.FeedPost

sealed class NewsFeedScreenState {

    object Initial: NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()
}
